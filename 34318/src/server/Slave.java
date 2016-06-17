package server;

import java.util.ArrayList;

import chat.ChatRoom;
import client.UserInfo;
import utility.Message;
import utility.Utilities;

public class Slave extends Thread {
	private Connection master;
	private Thread updateThread;
	private boolean updateUser = true;
	
	private ArrayList<String> onlineUsers;
	private String username, password;
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
				master.sendMessage("L100", null);
			} else {
				setParams(1, "Wrong credentials");
				master.sendMessage("L400", params);
			}
			startUpdateUserThread();
			break;
		case "L101": // Create User
			// Notify client if username already exists in database.
			userinformation = (UserInfo) message.getObject(); // Test if
																// conversion is
																// good.
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
		case "L103": // Logout
			if (master.isLoggedIn()) {
				master.setLoggedIn(false);
			}
			break;
		case "C100": // Invite Private Chat
			break;
		case "C101": // Start Private Group
			break;
		case "C102": // Start Public Group
			String chatID = Utilities.generateID(5);
			if (Server.db.addNewPublicChat(message.getParams()[0], chatID)) {
				ArrayList<ChatRoom> data = Server.db.generatePublicChatRoomsData();
				master.sendMessage("C100", null, data);
			} else {
				setParams(1, "Roomname taken");
				master.sendMessage("C400", params);
			}
			break;
			
		case "S100": // Broadcast Message
			chatID = message.getParams()[0];
			String msg = message.getParams()[1];		
			broadcastToPublicRoom(chatID, msg);
			
//			setParams(3, master.getUsername(), msg, chatID);
//			for(String value : Server.db.getPublicRooms().get(chatID).getChatUsers()) {
//				Server.db.getActiveUsers().get(value).sendMessage("S100", params);
//			}
			
//			for(int i = 0; i < Server.db.getPublicRooms().get(chatID).getChatUsers().size(); i++) {
//				String usr = Server.db.getPublicRooms().get(chatID).getChatUsers().get(i);
//				System.out.println(usr);
//				setParams(3, master.getUsername(), msg, chatID);
//				Server.db.getActiveUsers().get(usr).sendMessage("S100", params);
//			}
			break;
		case "S101": // Private Message
			break;
		case "V100": // Add Friend
			break;
		case "V101": // Accept Friend
			break;
		case "V102": // Delete Friend
			break;
		case "V103": // Block Person
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
		case "G101": // Join Private Group
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
