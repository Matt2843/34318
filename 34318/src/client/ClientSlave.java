package client;

import chat.ChatRoom;
import gui.GUIEngine;
import gui.MainFrame;
import gui.PanelLeft;
import gui.PanelRight;
import gui.PopUp;
import gui.Profile;
import utility.Message;

public class ClientSlave extends Thread {
	
	private String targetChat;
	private String targetUser;
	private String targetChatname;
	private String reason;
	
	
	private UserInfo profile;
	
	public void translate(Message message) {
		switch(message.getCommand()) {
		case "L100":	// LOGIN SUCCEEDED
			profile = (UserInfo) message.getObject();
			MainFrame.client.setProfile(profile);
			MainFrame.leftPanel.setProfileProperties();
			Profile.setUsername(profile.getUsername());
			GUIEngine.mainFrame.setUsername(profile.getUsername());
			MainFrame.DLogin.setVisible(false);
			GUIEngine.mainFrame.setVisible(true);
			break;
		case "L400":	// LOGIN FAILED
			new PopUp("Wrong password",MainFrame.DLogin);
			break;
		case "L101": 
			break;
		case "L401":
			new PopUp("Username already exist",MainFrame.DLogin);
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
			targetUser = message.getParams()[0] + " :";
			while(targetUser.length() <= 10) {
				targetUser += " "; 
			}
			String msg = targetUser + message.getParams()[1];
			targetChat = message.getParams()[2];
			PanelRight.chatTabs.get(targetChat).appendToTextArea(msg);
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
		case "V102": // User removed you from friends list.
			targetUser = message.getParams()[0];
			MainFrame.client.getProfile().removeFriend(targetUser);
			MainFrame.leftPanel.profileUpdatedNotification(1);
			new PopUp(targetUser +" has been deleted",GUIEngine.mainFrame);
			break;
		case "V402":
			break;
			
		case "G100":
			break;
		case "G400":
			break;
		case "G101": // Joining private chat
			targetUser = message.getParams()[0];
			targetChat = message.getParams()[1];
			MainFrame.client.getProfile().addPersonalChat(targetUser, targetChat);
			ChatRoom newPrivateChat = new ChatRoom(targetUser, targetChat);
			MainFrame.rightPanel.addTab(newPrivateChat);
			break;
		case "G401":
			break;
		case "G102": // Joining private group
			targetChat = message.getParams()[0];
			targetChatname = message.getParams()[1];
			ChatRoom newPrivateGroup = new ChatRoom(targetChatname, targetChat);
			MainFrame.rightPanel.addTab(newPrivateGroup);
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
			if(!MainFrame.leftPanel.isSearching()){
				PanelLeft.PLPublicChats.setList(message.getObject());
			}
			
			break;
		case "U101":
			break;
		case "U102":
			break;
		case "U103": // Online users list update.
			targetChat = message.getParams()[0];
			PanelRight.chatTabs.get(targetChat).getOnlineUsers().setList(message.getObject());
			break;
		case "U104": // Friend Request Received
			MainFrame.client.getProfile().addFriendRequest(message.getParams()[0]);
			MainFrame.leftPanel.profileUpdatedNotification(0);
			break;
		case "U105": // Friend added.
			MainFrame.client.getProfile().addFriend(message.getParams()[0]);
			MainFrame.leftPanel.profileUpdatedNotification(1);
			break;
		
		default:
			break;
		}
	}

}
