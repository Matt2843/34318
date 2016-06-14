package chat;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatRoom implements Serializable {
	private static final long serialVersionUID = -48495501677067685L;
	
	private String chatName = null;
	private String chatHistory = null;
	private ArrayList<String> chatUsers = null;
	private ArrayList<String> chatModeratiors = null;
	private ArrayList<String> chatAdmins = null;
	
	
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
	public ArrayList<String> getChatModeratiors() {
		return chatModeratiors;
	}
	public void setChatModeratiors(ArrayList<String> chatModeratiors) {
		this.chatModeratiors = chatModeratiors;
	}
	public ArrayList<String> getChatAdmins() {
		return chatAdmins;
	}
	public void setChatAdmins(ArrayList<String> chatAdmins) {
		this.chatAdmins = chatAdmins;
	}
	public String getChatHistory() {
		return chatHistory;
	}
	
	

}
