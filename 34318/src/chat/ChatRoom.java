package chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class ChatRoom implements Serializable {
	private static final long serialVersionUID = -48495501677067685L;
	
	private String chatName = "";
	private String chatID = "";
	
	private ArrayList<String> chatUsers;
	private ArrayList<String> blockedUsers;
	private ArrayList<String> chatModerators;
	private ArrayList<String> chatAdmins;
	
	private StyledDocument chatHistory;
	
	public ChatRoom(String chatID) {
		this.chatID = chatID;
		chatUsers = new ArrayList<String>();
		chatModerators = new ArrayList<String>();
		chatAdmins = new ArrayList<String>();
		blockedUsers = new ArrayList<String>();
	}
	
	public ChatRoom(String chatName, String chatID){
		this.chatName = chatName;
		this.chatID = chatID;
		chatUsers = new ArrayList<String>();
		chatModerators = new ArrayList<String>();
		chatAdmins = new ArrayList<String>();
		blockedUsers = new ArrayList<String>();
	}
	
	public void addStringToHistory(String s) {
		String timestamp = "[" + new Date().toString().substring(11, 16) + "] ";
		String append = timestamp + "    " + s + "\n";
		try {
			chatHistory.insertString(chatHistory.getLength(), append, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void blockUser(String user) {
		if(blockedUsers.contains(user)) {
			blockedUsers.remove(user);
		}
	}
	
	public void unblockUser(String user) {
		if(!blockedUsers.contains(user)) {
			blockedUsers.add(user);
		}
	}
	
	public void addUser(String user) {
		if(!chatUsers.contains(user)) {
			chatUsers.add(user);
		}
	}
	
	public void removeUser(String user) {
		if(chatUsers.contains(user)) {
			chatUsers.remove(user);
		}
	}
	
	public String getChatName() {
		return chatName;
	}
	
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}
	
	public ArrayList<String> getChatUsers() {
		return chatUsers;
	}
	
	public void setChatUsers(ArrayList<String> chatUsers) {
		this.chatUsers = chatUsers;
	}
	
	public ArrayList<String> getChatAdmins() {
		return chatAdmins;
	}
	
	public void setChatAdmins(ArrayList<String> chatAdmins) {
		this.chatAdmins = chatAdmins;
	}
	
	public String getChatID() {
		return chatID;
	}
	
	public void setChatID(String chatID) {
		this.chatID = chatID;
	}

	public ArrayList<String> getChatModerators() {
		return chatModerators;
	}

	public void setChatModerators(ArrayList<String> chatModerators) {
		this.chatModerators = chatModerators;
	}
	
	@Override
	public String toString() {
		return chatName;
	}

	public StyledDocument getChatHistory() {
		return chatHistory;
	}

	public void setChatHistory(StyledDocument chatHistory) {
		this.chatHistory = chatHistory;
	}

}
