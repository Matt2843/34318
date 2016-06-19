package server;

import java.util.ArrayList;

import chat.ChatRoom;
import client.UserInfo;
import utility.Message;

public class Slave extends Thread {
	private Connection master;
	private Thread updateThread;
	private boolean updateUser = true;
	
	private ArrayList<String> onlineUsers;
	private String username, password, chatID, targetUser, targetChat;
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
				System.out.println(userinformation);
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
			chatID = message.getParams()[0];
			String msg = message.getParams()[1];		
			broadcastToPublicRoom(chatID, msg);
			break;
		case "S101": // Private Message
			break;
			
		case "V100": // Friend Request
			targetUser = message.getParams()[0];
			if(Server.db.getRegisteredUsers().containsKey(targetUser)) {
				Server.db.getRegisteredUsers().get(targetUser).addFriendRequest(master.getUsername());
				master.sendMessage("V100", null);
			} else {
				setParams(1, "User does not exist in database.");
				master.sendMessage("V400", params);
			}
			break;
		case "V101": // Accept Friend
			targetUser = message.getParams()[0];
			if(Server.db.getRegisteredUsers().containsKey(targetUser)) {
				Server.db.getRegisteredUsers().get(targetUser).addFriend(master.getUsername());
				Server.db.getRegisteredUsers().get(master.getUsername()).addFriend(targetUser);
				master.sendMessage("V101", null);
			} else {
				setParams(1, "User does not exist in database.");
				master.sendMessage("V401", params);
			}
			break;
		case "V102": // Delete Friend
			targetUser = message.getParams()[0];
			Server.db.getRegisteredUsers().get(master.getUsername()).removeFriend(targetUser);
			Server.db.getRegisteredUsers().get(targetUser).removeFriend(master.getUsername());
			master.sendMessage("V102", null);
			break;
		case "V103": // Block Person
			targetUser = message.getParams()[0];
			Server.db.getRegisteredUsers().get(master.getUsername()).blockUser(targetUser);
			break;
		case "V104": // Get Friends
			break;
			
		case "G100": // Join Public Chat
			chatID = message.getParams()[0];
			Server.db.getPublicRooms().get(chatID).addUser(master.getUsername());
			
			// Broadcast the event: User joined chat.
			onlineUsers = Server.db.generateOnlinePublicUsersData(chatID);
			broadcastObjectToPublicRoom(chatID, "U103", onlineUsers);
			break;
		case "G101": // Join Private Chat
			
			break;
		case "G102": // Remove Person from Chat
			break;
		case "G103": // Left Public Chat
			chatID = message.getParams()[0];
			Server.db.getPublicRooms().get(chatID).removeUser(master.getUsername());
			
			// Broadcast the event: User left chat.
			onlineUsers = Server.db.generateOnlinePublicUsersData(chatID);
			broadcastObjectToPublicRoom(chatID, "U103", onlineUsers);
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
			
		case "F100": // Upload File
			break;
		case "F101": // Download File
			break;
			
		case "U100": // Get PublicRooms data
			master.sendMessage("D100", null, Server.db.generatePublicChatRoomsData());
			break;
		case "U101": // Get PrivateRooms data
			break;
		case "U102": // Get Friends data
			break;
		case "U103": // Get online users data
			chatID = message.getParams()[0];
			master.sendMessage("U103", null, Server.db.generateOnlinePublicUsersData(chatID));
			break;
			
		default:
			break;
		}
	}
	
	private void broadcastToPublicRoom(String chatID, String msg) {
		setParams(3, master.getUsername(), msg, chatID);
		for(String value : Server.db.getPublicRooms().get(chatID).getChatUsers()) {
			Server.db.getActiveUsers().get(value).sendMessage("S100", params);
		}
	}
	
	private void broadcastObjectToPublicRoom(String chatID, String cmd, Object o) {
		setParams(1, chatID);
		for(String value : Server.db.getPublicRooms().get(chatID).getChatUsers()) {
			Server.db.getActiveUsers().get(value).sendMessage(cmd, params, o);
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
