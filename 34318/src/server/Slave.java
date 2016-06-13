package server;

import java.util.concurrent.SynchronousQueue;

import client.UserInfo;
import utility.Message;

public class Slave extends Thread {
	
	private Connection master;
	private String username, password;
	private UserInfo userinformation;
	
	public Slave(Connection master) {
		this.master = master;
	}
	
	public void decode(Message<String, Object> message) {
		String msgSplit[] = message.getString().split("#");
		switch (msgSplit[1]) {
		case "L100": // Login FORMAT: "SESSIONID#USERNAME#USERINFORMATION"
			username = msgSplit[2];
			password = msgSplit[3];
			if(Server.db.getRegisteredUsers().containsKey(username)) {
				userinformation = (UserInfo) message.getObject();
				if(Server.db.getRegisteredUsers().get(username).getPassword().equals(password)) {
					master.setLoggedIn(true);
					master.sendMessage("L100", userinformation);
				} else {
					master.sendMessage("L400#Wrong password.");
				}
			} else {
				master.sendMessage("L400#Username doesn't exist.");
			}
			break;
		case "L101": // Create User FORMAT: "SESSIONID#L101#USERNAME#USERINFORMATION"
			// Notify client if username already exists in database.
			System.out.println("HELLO WE RECEIVED STUFF");
			userinformation = (UserInfo) message.getObject(); // Test if conversion is good.
			username = userinformation.getUsername();
			if(!Server.db.getRegisteredUsers().containsKey(username)) {
				Server.db.registerNewUser(username, userinformation);
				Server.setServerStatus("User " + username + " added to database.");
				master.sendMessage("L101");
			} else { // Username taken
				master.sendMessage("L401#Username already exists");
			}
			break;
		case "L102": // Change Password FORMAT: "SESSIONID#L102#USERNAME#PASSWORD#NEWPASSWORD"
			if(master.isLoggedIn()) {
				username = msgSplit[2];
				password = msgSplit[3];	
				if(Server.db.getRegisteredUsers().get(username).getPassword().equals(password)) {
					Server.db.getRegisteredUsers().get(username).setPassword(msgSplit[4]);
				} else {
					master.sendMessage("L400#Wrong Password");
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
