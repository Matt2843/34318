package server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import chat.ChatRoom;
import client.UserInfo;
import utility.Message;
import utility.Utilities;

public class Slave extends Thread {
	private Connection master;
	private Thread updateThread;
	private boolean updateUser = true;
	
	private ByteArrayOutputStream baos;
	
	private ArrayList<String> onlineUsers;
	private String username, password, targetUser, targetChat, chatName, fileName, fileID;
	private UserInfo userinformation;
	
	private String[] params;

	public Slave(Connection master) {
		this.master = master;
	}

	public void translate(Message message) {
		switch (message.getCommand()) {
		case "L100": // Login
			username = message.getParams()[0];
			password = message.getParams()[1];
			if(Server.db.authenticateUser(username, password)) {
				master.setUsername(username);
				Server.db.addNewConnection(username, master);
				userinformation = Server.db.getRegisteredUsers().get(username);
				master.sendMessage("L100", null, userinformation);
			} else {
				setParams(1, "Wrong credentials");
				master.sendMessage("L400", params);
			}
			startUpdateUserThread();
			break;
		case "L101": // Create User
			userinformation = (UserInfo) message.getObject(); 
			username = userinformation.getUsername();
			if (!Server.db.getRegisteredUsers().containsKey(username)) {
				Server.db.registerNewUser(username, userinformation);
				Server.setServerStatus("User " + username + " added to database.");
				master.sendMessage("L101", null);
			} else { // Username taken
				setParams(1, "Username already exists.");
				master.sendMessage("L401", params);
			}
			break;
		case "L102": // Change Password
			if (master.isLoggedIn()) {
				username = message.getParams()[0];
				password = message.getParams()[1];
				if (Server.db.getRegisteredUsers().get(username).getPassword().equals(password)) {
					Server.db.getRegisteredUsers().get(username).setPassword(message.getParams()[2]);
				} else {
					setParams(1, "Wrong Password");
					master.sendMessage("L400", params);
				}
			}
			break;
		case "L103": // Logout - TODO: IMPLEMENT
			if (master.isLoggedIn()) {
				master.setLoggedIn(false);
			}
			break;
			
		case "C100": // Create Private Group
			break;
		case "C101": // Start Public Group
			if (Server.db.createNewPublicChat(message.getParams()[0])) {
				master.sendMessage("C101", null, Server.db.generatePublicChatRoomsData());
			} else {
				setParams(1, "Roomname taken");
				master.sendMessage("C401", params);
			}
			break;
			
		case "S100": // Broadcast Message
			targetChat = message.getParams()[0];
			String msg = message.getParams()[1];		
			broadcastToRoom(targetChat, msg);
			break;
		case "S101": // Private Message
			break;
			
		case "V100": // Friend Request
			targetUser = message.getParams()[0];
			if(Server.db.getRegisteredUsers().containsKey(targetUser)) {
				// Notify target user of the pending friend request if online.
				if(Server.db.getActiveUsers().containsKey(targetUser)) {
					setParams(1, master.getUsername());
					Server.db.getActiveUsers().get(targetUser).sendMessage("U104", params);
				}
			} else {
				setParams(1, "User not found in database.");
				master.sendMessage("V400", params);
			}
			break;
		case "V101": // Accept Friend
			targetUser = message.getParams()[0];
			String param2 = message.getParams()[1];
			
			// Notify src user if online.
			if(master.isAlive() && param2.equals("accept")) {
				setParams(1, targetUser);
				master.sendMessage("U105", params);
			}
			
			// Notify target user if online.
			if(Server.db.getActiveUsers().containsKey(targetUser) && param2.equals("accept")) {
				setParams(1, master.getUsername());
				Server.db.getActiveUsers().get(targetUser).sendMessage("U105", params);
			}	
			break;
		case "V102": // Remove Friend
			targetUser = message.getParams()[0];
			
			if(Server.db.getActiveUsers().containsKey(targetUser)) {
				setParams(1, master.getUsername());
				Server.db.getActiveUsers().get(targetUser).sendMessage("V102", params);
			} else {
				Server.db.getRegisteredUsers().get(targetUser).removeFriend(master.getUsername());
			}
			break;
			
		case "G100": // Join Public Chat
			targetChat = message.getParams()[0];
			Server.db.getPublicRooms().get(targetChat).addUser(master.getUsername());
			
			// Broadcast the event: User joined chat.
			onlineUsers = Server.db.generateOnlineUsersData(targetChat);
			broadcastObjectToRoom(targetChat, "U103", onlineUsers);
			break;
		case "G101": // Join Private Chat
			targetUser = message.getParams()[0];
			
			// If the users already have a chat going, get it from the server else start a new.
			if(Server.db.getRegisteredUsers().get(targetUser).getSavedPersonalChats().containsKey(master.getUsername())) {
				targetChat = Server.db.getRegisteredUsers().get(targetUser).getSavedPersonalChats().get(master.getUsername());
			} else {
				targetChat = Server.db.createNewPrivateChat();
			}
			
			// Add users to chat
			Server.db.getPrivateRooms().get(targetChat).addUser(master.getUsername());	
			Server.db.getPrivateRooms().get(targetChat).addUser(targetUser);
			
			// Generate Success message
			if(master.isAlive()) {
				setParams(2, targetUser, targetChat);
				master.sendMessage("G101", params);
			}
			
			
			// Annoy target user
			if(Server.db.getActiveUsers().containsKey(targetUser)) {
				setParams(2, master.getUsername(), targetChat);
				Server.db.getActiveUsers().get(targetUser).sendMessage("G101", params);
			}
			
			// Broadcast the event: User joined chat.
			broadcastObjectToRoom(targetChat, "U103", Server.db.generateOnlineUsersData(targetChat));
			break;
		case "G102": // Invitations to private group.
			targetChat = message.getParams()[0];
			chatName = message.getParams()[1];
			// Check if there is more than two users in the chat room.
			if(Server.db.getPrivateRooms().get(targetChat).getChatUsers().size() > 2) {
				// If there is more than two users in the room, directly add the invited people to the room.
				setParams(2, targetChat, chatName);
				for(int i = 2; i < message.getParams().length; i++) {
					targetUser = message.getParams()[i];
					// Add the user to the chat room in the database.
					Server.db.getPrivateRooms().get(targetChat).addUser(targetUser);
					// Check if target user is online
					if(Server.db.getActiveUsers().containsKey(targetUser)) {
						// Invite target user to the chat room.
						Server.db.getActiveUsers().get(targetUser).sendMessage("G102", params);
					}
					broadcastObjectToRoom(targetChat, "U103", Server.db.generateOnlineUsersData(targetChat));
				}
			} else { // Create a new chat room and invite everyone including host.
				String newChat = Server.db.createNewPrivateChat();
				setParams(2, newChat, chatName);
				for(String username : Server.db.getPrivateRooms().get(targetChat).getChatUsers()) {
					Server.db.getPrivateRooms().get(newChat).addUser(username);
					if(Server.db.getActiveUsers().containsKey(username)) {
						Server.db.getActiveUsers().get(username).sendMessage("G102", params);
					}
				}
				for(int i = 2; i < message.getParams().length; i++) {
					targetUser = message.getParams()[i];
					Server.db.getPrivateRooms().get(newChat).addUser(targetUser);
					if(Server.db.getActiveUsers().containsKey(targetUser)) {
						Server.db.getActiveUsers().get(targetUser).sendMessage("G102", params);
					}
				}
				broadcastObjectToRoom(newChat, "U103", Server.db.generateOnlineUsersData(newChat));
			}
			
			break;
		case "G103": // Left Public Chat
			targetChat = message.getParams()[0];
			if(Server.db.getPublicRooms().containsKey(targetChat)) {
				Server.db.getPublicRooms().get(targetChat).removeUser(master.getUsername());
			} else if(Server.db.getPrivateRooms().containsKey(targetChat)) {
				Server.db.getPrivateRooms().get(targetChat).removeUser(master.getUsername());
			}
			
			// Broadcast the event: User left chat.
			onlineUsers = Server.db.generateOnlineUsersData(targetChat);
			broadcastObjectToRoom(targetChat, "U103", onlineUsers);
			break;
		case "G104": // Remove Person from chat.
			targetUser = message.getParams()[0];
			targetChat = message.getParams()[1];
			if(Server.db.getPublicRooms().containsKey(targetChat)) { // HANDLE PUBLIC ROOM SITUATION
				if(Server.db.getPublicRooms().get(targetChat).getChatAdmins().contains(master.getUsername()) || 
				   Server.db.getPublicRooms().get(targetChat).getChatModerators().contains(master.getUsername())) {
					// Kick and ban person here  -
				}
			} else if (Server.db.getPrivateRooms().containsKey(targetChat)) { // HANDLE PRIVE ROOM SITUATION
				// TODO: Implement kick person from private room.
			} else {
				setParams(1, "Chat does not exist in database.");
				master.sendMessage("G402", params);
			}
			break;
			
		case "F100": // Begin Uploading File
			fileID = Utilities.generateID(10, Utilities.FILE);
			while(Server.db.getStoredFiles().containsKey(fileID)) {
				fileID = Utilities.generateID(10, Utilities.FILE);
			}
			targetChat = message.getParams()[0];
			fileName = message.getParams()[1];
			baos = new ByteArrayOutputStream(100);
			break;
		case "F101":
			receiveFile(message.getObject(), message.getObjectTwo());
			break;
		case "F102": // Download File
			break;
			
		case "U100": // Get PublicRooms data
			master.sendMessage("D100", null, Server.db.generatePublicChatRoomsData());
			break;
		case "U101": // Get PrivateRooms data
			break;
		case "U102": // Get Friends data
			break;
		case "U103": // Get online users data
			targetChat = message.getParams()[0];
			master.sendMessage("U103", null, Server.db.generateOnlineUsersData(targetChat));
			break;
		case "U104": // Updated profile
			userinformation = (UserInfo) message.getObject();
			System.out.println("FRIEND REQUESTS ON USER: " + userinformation.getFriendRequests());
			Server.db.getRegisteredUsers().remove(master.getUsername());
			Server.db.getRegisteredUsers().put(master.getUsername(), userinformation);
			break;
			
		default:
			break;
		}
	}
	
	private void receiveFile(Object object, Object objectTwo) {
		Integer bytesRead = (Integer) object;
		byte[] buffer = (byte[]) objectTwo;
		try {
			baos.write(buffer, 0, bytesRead);
			if(bytesRead < 100) {
				System.out.println("File received, storing the file in the database...!");
				byte[] receivedFile = baos.toByteArray();
				Server.db.getStoredFiles().put(fileID, receivedFile);
				baos.close();
				broadcastLinkToRoom();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendFile(String path, String targetRoom) {
		System.out.println(path);
		File file = new File(path);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			byte [] buffer = new byte[100]; //Størrelsen af bufferen
			Integer bytesRead = 0;
			String[] params = {targetRoom};
			master.sendMessage("F100", params);
			while ((bytesRead = fis.read(buffer)) > 0) {
				master.sendMessage("F101", null, bytesRead, Arrays.copyOf(buffer, buffer.length));
			}
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void broadcastToRoom(String chatID, String msg) {
		setParams(3, master.getUsername(), msg, chatID);
		if(Server.db.getPublicRooms().containsKey(chatID)) { // Handle the public rooms broadcast
			for(String value : Server.db.getPublicRooms().get(chatID).getChatUsers()) {
				Server.db.getActiveUsers().get(value).sendMessage("S100", params);
			}
		} else if (Server.db.getPrivateRooms().containsKey(chatID)) { // Handle the private rooms broadcast
			for(String value : Server.db.getPrivateRooms().get(chatID).getChatUsers()) {
				Server.db.getActiveUsers().get(value).sendMessage("S100", params);
			}
		}
	}
	
	private void broadcastLinkToRoom() {
		setParams(3, targetChat, fileName, fileID);
		if(Server.db.getPrivateRooms().containsKey(targetChat)) {
			for(String username : Server.db.getPrivateRooms().get(targetChat).getChatUsers()) {
				if(Server.db.getActiveUsers().containsKey(username)) {
					Server.db.getActiveUsers().get(username).sendMessage("F103", params);
				}
			}
		}
	}
	
	private void broadcastObjectToRoom(String chatID, String cmd, Object o) {
		setParams(1, chatID);
		if(Server.db.getPublicRooms().containsKey(chatID)) {
			for(String value : Server.db.getPublicRooms().get(chatID).getChatUsers()) {
				if(Server.db.getActiveUsers().containsKey(value)) {
					Server.db.getActiveUsers().get(value).sendMessage(cmd, params, o);
				}
			}
		} else if(Server.db.getPrivateRooms().containsKey(chatID)) {
			for(String value : Server.db.getPrivateRooms().get(chatID).getChatUsers()) {
				if(Server.db.getActiveUsers().containsKey(value)) {
					Server.db.getActiveUsers().get(value).sendMessage(cmd, params, o);
				}
			}
		}
		
	}
	
	private void setParams(int length, String... p) {
		params = new String[length];
		for (int i = 0; i < length; i++) {
			params[i] = p[i];
		}
	}
	
	private boolean sendUpdatedInfo() {
		if(updateUser) {
			ArrayList<ChatRoom> publicRoomsData = Server.db.generatePublicChatRoomsData();
			if(publicRoomsData.size() > 0) master.sendMessage("U100", null, publicRoomsData);
			return true;
		} else return false;
	}
	
	private void startUpdateUserThread() {
		updateThread = new Thread(new Runnable() {
			public void run() {
				int seconds = 20;
				while(true) {
					if(!sendUpdatedInfo()) break;
					try {
						Thread.sleep(seconds * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		updateThread.start();
	}
	
	public void stopUpdateUserThread() {
		updateUser = false;
		try {
			if(updateThread != null) {
				updateThread.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
