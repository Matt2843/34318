package client;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 3863758677993591803L;
	
	private ArrayList<String> friends;
	private ArrayList<String> savedRooms;
	private ArrayList<String> blocked;
	private ArrayList<String> friendRequests;
	
	private String username;
	private String password;
	
	public UserInfo() {
	}
	public UserInfo(String username, String password) {
		this.password = password; this.username = username;
	}
	public ArrayList<String> getFriends() {
		return friends;
	}
	public void friends(String alias) {
		friends.add(alias);
	}
	public ArrayList<String> getSavedRooms() {
		return savedRooms;
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
	public void removeFriend(String username) {
		if(friends.contains(username)) {
			friends.remove(username);
		}
	}
	public void addFriend(String username) {
		if(!friends.contains(username)) {
			friends.add(username);
		}
	}
	public void removeFriendRequest(String requestingUser) {
		if(friendRequests.contains(requestingUser)) {
			friendRequests.remove(requestingUser);
		}
	}
	public void addFriendRequest(String requestingUser) {
		if(!friendRequests.contains(requestingUser)) {
			friendRequests.add(requestingUser);
		}
	}
	public ArrayList<String> getFriendRequests() {
		return friendRequests;
	}	
}
