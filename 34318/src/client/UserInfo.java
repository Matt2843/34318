package client;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 3863758677993591803L;
	
	private ArrayList<String> friends = null;
	private ArrayList<String> savedRooms = null;
	
	private String username = null;
	private String password = null;
	private String alias = null;
	
	public UserInfo() {
	}
	
	public UserInfo(String username, String password) {
		this.password = password; this.username = username;
	}
	
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	
}
