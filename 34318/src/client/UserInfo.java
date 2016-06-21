package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 3863758677993591803L;
	
	private String username;
	private String password;
	
	private ArrayList<String> friends;
	private ArrayList<String> blocked;
	private ArrayList<String> friendRequests;
	
	private HashMap<String, String> savedPersonalChats; // K: Username V: ChatID
	private HashMap<String, String> savedGroupChats; // K: Username V: ChatID
	
	public UserInfo() {
		initializeData();
	}
	public UserInfo(UserInfo userInfo) {
		initializeData();
		username = userInfo.getUsername();
		password = userInfo.getPassword();
		friends = userInfo.getFriends();
		blocked = userInfo.getBlocked();
		friendRequests = userInfo.getFriendRequests();
		savedPersonalChats = userInfo.getSavedPersonalChats();
		savedGroupChats = userInfo.getSavedGroupChats();
	}
	public UserInfo(String username, String password) {
		this.password = password; this.username = username;
		initializeData();
	}

	private void initializeData() {
		friends = new ArrayList<String>();
		blocked = new ArrayList<String>();
		friendRequests = new ArrayList<String>();
		savedPersonalChats = new HashMap<String, String>();
		savedGroupChats = new HashMap<String, String>();
	}
	public void addPersonalChat(String name, String chatID) {
		if(!savedPersonalChats.containsKey(name)) {
			savedPersonalChats.put(name, chatID);
		}
	}
	public void removePersonalChat(String name) {
		if(savedPersonalChats.containsKey(name)) {
			savedPersonalChats.remove(name);
		}
	}
	public void addGroupChat(String name, String chatID) {
		if(!savedGroupChats.containsKey(name)) {
			savedGroupChats.put(name, chatID);
		}
	}
	public void removeGroupChat(String name) {
		if(savedGroupChats.containsKey(name)) {
			savedGroupChats.remove(name);
		}
	}
	public ArrayList<String> getFriends() {
		return friends;
	}
	public void friends(String alias) {
		friends.add(alias);
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<String> getBlocked() {
		return blocked;
	}
	public void unblockUser(String user) {
		if(blocked.contains(user)) {
			blocked.remove(user);
		}
	}
	public void blockUser(String user) {
		if(!blocked.contains(user)) {
			blocked.add(user);
		}
	}
	
	public void addFriend(String username) {
		if(!friends.contains(username)) {
			friends.add(username);
		}
	}
	
	public void removeFriend(String username) {
		if(friends.contains(username)) {
			friends.remove(username);
		}
	}

	public void addFriendRequest(String requestingUser) {
		if(!friendRequests.contains(requestingUser)) {
			friendRequests.add(requestingUser);
		}
	}
	
	public void removeFriendRequest(String requestingUser) {
		if(friendRequests.contains(requestingUser)) {
			friendRequests.remove(requestingUser);
		}
	}
	
	public ArrayList<String> getFriendRequests() {
		return friendRequests;
	}	

	public HashMap<String, String> getSavedPersonalChats() {
		return savedPersonalChats;
	}
	
	public HashMap<String, String> getSavedGroupChats() {
		return savedGroupChats;
	}
}
