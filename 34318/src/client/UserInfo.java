package client;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 3863758677993591803L;
	
	private ArrayList<String> friends = null;
	private ArrayList<String> savedRooms = null;
	
	private String username;
	private String password;
	
	public ArrayList<String> getFriends() {
		return friends;
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

	
}
