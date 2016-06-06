package server;

import utility.Message;

public class Slave extends Thread {
	
	@Override
	public void run() {
		
	}
	
	public void decode(Message<String, Object> message) {
		String msgSplit[] = message.getString().split("#");
		switch (msgSplit[0]) {
		case "L100": // Login
			System.out.println("Login request received.");
			break;
		case "L101": // Create User
			break;
		case "L102": // Change Password
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
