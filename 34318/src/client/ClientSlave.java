package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
	
	private String fileID;
	private String fileName;
	private FileOutputStream fos = null; 
	
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
			
		case "F100": // UPLOAD FILE SUCCEEDED
			break;
		case "F400": // UPLOAD FILE FAILED
			break;
		case "F101": // Start downloading file
			fileName = message.getParams()[0];
			saveFile(fileName);
			break;
		case "F102": // Receive file.
			receiveFile(message.getObject(), message.getObjectTwo());
			break;
		case "F103": // Received file link.
			targetChat = message.getParams()[0];
			fileName = message.getParams()[1];
			fileID = message.getParams()[2];
			String username = message.getParams()[3];
			PanelRight.chatTabs.get(targetChat).appendLinkToArea(username, fileID, fileName);
			break;
			
		case "S100": // Received Message
			targetUser = message.getParams()[0] + ": ";
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
			ChatRoom newPrivateChat = null;
			if(MainFrame.client.getProfile().getSavedPersonalChats().containsKey(targetUser)) {
				newPrivateChat = MainFrame.client.getProfile().getSavedPersonalChats().get(targetUser);
			} else {
				newPrivateChat = new ChatRoom(targetUser, targetChat);
				MainFrame.client.getProfile().addPersonalChat(targetUser, newPrivateChat);
			}
			MainFrame.rightPanel.addTab(newPrivateChat);
			break;
		case "G401":
			break;
		case "G102": // Joining private group
			targetChat = message.getParams()[0];
			targetChatname = message.getParams()[1];
			ChatRoom newPrivateGroup = new ChatRoom(targetChatname, targetChat);
			// ADD PRIVATE GRP TO PROFILE...
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
	
	private void saveFile(String filename) {
		if(!new File("downloads").exists()) {
			new File("downloads").mkdirs();
		}
		try {
			fos = new FileOutputStream("downloads/"+filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void receiveFile(Object o, Object o2) {
		Integer bytesRead = (Integer) o;
		byte[] buffer = (byte[]) o2;
        try {
            fos.write(buffer, 0, bytesRead);
            if(bytesRead < 20000) {
                System.out.println("File Stream Done!");
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
