package client;

import chat.ChatRoom;
import gui.GUIEngine;
import gui.MainFrame;
import gui.PanelLeft;
import gui.PanelRight;
import gui.Profile;
import utility.Message;

public class ClientSlave extends Thread {
	
	private String chatID = null;
	private String reason = null;
	private String targetUser = null;
	
	public void translate(Message message) {
		switch(message.getCommand()) {
		case "L100":	// LOGIN SUCCEEDED
			UserInfo profile = (UserInfo) message.getObject();
			MainFrame.client.setProfile(profile);
			Profile.setUsername(profile.getUsername());
			GUIEngine.mainFrame.setUsername(profile.getUsername());
			MainFrame.DLogin.setVisible(false);
			GUIEngine.mainFrame.setVisible(true);
			break;
		case "L400":	// LOGIN FAILED
			break;
		case "L101": 
			break;
		case "L401":
			break;
		case "L102":
			break;
		case "L402":
			break;
			
		case "F100":
			break;
		case "F400":
			break;
		case "F101":
			break;
		case "F401":
			reason = message.getParams()[0];
			break;
			
		case "S100": // Received Message
			targetUser = message.getParams()[0];
			String msg = targetUser + ":   " +message.getParams()[1];
			chatID = message.getParams()[2];
			PanelRight.chatTabs.get(chatID).appendToTextArea(msg);
			break;
		case "S101":
			break;
		
		case "V100":
			break;
		case "V400":
//			GUIEngine.mainFrame.
			break;
		case "V101":
			break;
		case "V401":
			break;
		case "V102":
			break;
		case "V402":
			break;
			
		case "G100":
			break;
		case "G400":
			break;
		case "G101": // Joining private chat
			targetUser = message.getParams()[0];
			chatID = message.getParams()[1];
			System.out.println(targetUser + " id: " + chatID);
			MainFrame.client.getProfile().addPersonalChat(targetUser, chatID);
			ChatRoom newPrivateChat = new ChatRoom(targetUser, chatID);
			MainFrame.rightPanel.addTab(newPrivateChat);
			break;
		case "G401":
			break;
		case "G102":
			break;
		case "G402":
			break;
			
		case "C100":
			break;
		case "C400":
			break;
		case "C101": // Public Chat Creation Succeeded.
			PanelLeft.PLPublicChats.setList(message.getObject());
			break;
		case "C401":
			break;
		case "C402": // Public Chat Creation Failed.
			break;
			
		case "U100": // Public Chats list update.
			PanelLeft.PLPublicChats.setList(message.getObject());
			break;
		case "U101":
			break;
		case "U102":
			break;
		case "U103": // Online users list update.
			chatID = message.getParams()[0];
			PanelRight.chatTabs.get(chatID).getOnlineUsers().setList(message.getObject());
			break;
		case "U104": // Profile update
			MainFrame.client.setProfile((UserInfo) message.getObject());
			// MainFrame.client.getProfile().getFriendRequests() - access friend requests
			break;
		
		default:
			break;
		}
	}

}
