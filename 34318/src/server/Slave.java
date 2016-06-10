package server;

import utility.Message;

public class Slave extends Thread {
	
	private Connection master;
	private String username, password;
	
	public Slave(Connection master) {
		this.master = master;
	}
	
	public void decode(Message<String, Object> message) {
		String msgSplit[] = message.getString().split("#");
		switch (msgSplit[1]) {
		case "L100": // Login FORMAT: "SESSIONID#USERNAME#PASSWORD"
			username = msgSplit[2]; password = msgSplit[3];
			if(Server.db.getRegisteredUsers().get(username).equals(password)) {
				master.setLoggedIn(true);
				master.sendMessage("L100");
			} else {
				master.sendMessage("L400#Login failed.");
			}
			break;
		case "L101": // Create User FORMAT: "SESSIONID#L101#USERNAME#PASSWORD
			// Notify client if username already exists in database.
			username = msgSplit[2]; password = msgSplit[3];
			if(!master.isLoggedIn() && !Server.db.getRegisteredUsers().containsKey(username)) {
				Server.db.registerNewUser(username, password);
			} else {
				master.sendMessage("L401#User already exists");
			}
			break;
		case "L102": // Change Password
			password = msgSplit[2]; // Current password
			if(Server.db.getRegisteredUsers().containsKey(username)) {
				if(Server.db.getRegisteredUsers().get(username).equals(password)) {
					password = msgSplit[3]; // Change password to new password.
					Server.db.getRegisteredUsers().remove(username);
					Server.db.registerNewUser(username, password);
				}
			}
			break;
		case "L103": // Logout
			break;
		case "C100": // Invite Private Chat
			break;
		case "C101": // Start Private Group
			break;
		case "C102": // Start Public Group
			break;
		case "S100": // Broadcast Message
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
		case "G100": // Join Public Chat
			break;
		case "G101": // Join Private Group
			break;
		case "G102": // Remove Person from Chat
			break;
		case "F100": // Upload File
			break;
		case "F101": // Download File
			break;
		default:
			break;
		}
	}
	
}
