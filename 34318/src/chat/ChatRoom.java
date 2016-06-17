package chat;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatRoom implements Serializable {
	private static final long serialVersionUID = -48495501677067685L;
	
	private String chatName = "";
	private String chatID = "";
	private String chatHistory = "";
	
	private ArrayList<String> chatUsers;
	private ArrayList<String> chatModerators;
	private ArrayList<String> chatAdmins;
	
	public ChatRoom(String chatName, String chatID){
		this.chatName = chatName;
		this.chatID = chatID;
		chatUsers = new ArrayList<String>();
		chatModerators = new ArrayList<String>();
		chatAdmins = new ArrayList<String>();
	}
	
	public void addUser(String user) {
		if(!chatUsers.contains(user)) {
			chatUsers.add(user);
		}
	}
	
	public void removeUser(String user) {
		if(chatUsers.contains(user)) {
			removeUser(user);
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
	
	public String getChatHistory(String room) {
		return chatHistory;
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

	public String getChatHistory() {
		return chatHistory;
	}
	
	@Override
	public String toString() {
		return chatName;
	}

}
