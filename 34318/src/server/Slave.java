package server;

import java.util.ArrayList;

import chat.ChatRoom;
import client.UserInfo;
import utility.Message;
import utility.Utilities;

public class Slave extends Thread {

	private Connection master;
	private String username, password;
	private UserInfo userinformation;
	private String[] params;

	private ArrayList<ChatRoom> privateRoom = new ArrayList<ChatRoom>();
	private ArrayList<String> friends = new ArrayList<String>();

	public Slave(Connection master) {
		this.master = master;
		updateUser();
	}

	private void setParams(int length, String... p) {
		params = new String[length];
		for (int i = 0; i < length; i++) {
			params[i] = p[i];
		}
	}
	
	private void updateUser() {
		new Thread(new Runnable() {
			public void run() {
				int seconds = 20;
				while(true) {
					ArrayList<ChatRoom> publicRoomsData = Server.db.generatePublicChatRoomsData();
					if(publicRoomsData.size() > 0) master.sendMessage("U100", null, publicRoomsData);
					try {
						Thread.sleep(seconds * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void translate(Message message) {
		switch (message.getCommand()) {
		case "L100": // Login
			System.out.println(Server.db.getRegisteredUsers().keySet());
			username = message.getParams()[0];
			password = message.getParams()[1];
			if (Server.db.getRegisteredUsers().get(username).getPassword().equals(password)) {
				Server.db.addNewConnection(username, master);
				master.sendMessage("L100", null);
			} else {
				setParams(1, "Wrong credentials");
				master.sendMessage("L400", params);
			}
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
				System.out.println(data);
				master.sendMessage("C100", null, data);
			} else {
				setParams(1, "Roomname taken");
				master.sendMessage("C400", params);
			}
			break;
		case "S100": // Broadcast Message
			System.out.println(message.toString());
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
			friends.add("Friend 1");
			master.sendMessage("V104", null, friends);
			break;
		case "G100": // Join Public Chat
			break;
		case "G101": // Join Private Group
			String name = message.getParams()[0];
			privateRoom.add(new ChatRoom(name));
			master.sendMessage("G101", null, privateRoom);
			break;
		case "G102": // Remove Person from Chat
			break;
		case "F100": // Upload File
			break;
		case "F101": // Download File
			break;
		case "D100": // Get PublicRooms data
			master.sendMessage("D100", null, Server.db.generatePublicChatRoomsData());
			break;
		case "D101": // Get PrivateRooms data
			privateRoom.add(new ChatRoom("User 1"));
			master.sendMessage("D101", null, privateRoom);
			break;
		default:
			break;
		}
	}

}
