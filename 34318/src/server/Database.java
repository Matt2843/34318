package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import chat.ChatRoom;
import client.UserInfo;

@SuppressWarnings("unchecked")
public class Database {
	private HashMap<String, Connection> activeUsers = new HashMap<String, Connection>(); // K: SessionID 	V: Connection
	private HashMap<String, Object> storedFiles = null;									 // K: FileID		V: File
	private HashMap<String, UserInfo> registeredUsers = null;								 // CHANGE THIS
	private HashMap<String, ChatRoom> publicRooms = null;								 // K: ChatID		V: ChatRoom

	public Database() {
		String path = "data/registeredUsers.db";
		try {
			registeredUsers = new File(path).exists() ? (HashMap<String, UserInfo>) readFileToObject(path) : new HashMap<String, UserInfo>();
			path = "data/publicRooms.db";
			publicRooms = new File(path).exists() ? (HashMap<String, ChatRoom>) readFileToObject(path) : new HashMap<String, ChatRoom>();
			path = "data/storedFiles.db";
			storedFiles = new File(path).exists() ? (HashMap<String, Object>) readFileToObject(path) : new HashMap<String, Object>();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		System.out.println(registeredUsers.keySet());
	}
	
	public void registerNewUser(String username, UserInfo userinformation) {
		registeredUsers.put(username, userinformation);
	}
	
	public void addNewConnection(String sessionID, Connection connection) {
		activeUsers.put(sessionID, connection);
	}

	public void updateAndSaveDatabase() {
		if(!new File("data").exists()) {
			File dir = new File("data");
			dir.mkdir();
		}
		try {
			saveObjectToFile("data/registeredUsers.db", registeredUsers);
			saveObjectToFile("data/publicRooms.db", publicRooms);
			saveObjectToFile("data/storedFiles.db", storedFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Object readFileToObject(String path) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(new File(path));
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object target = ois.readObject();
		ois.close();
		return target;
	}

	private void saveObjectToFile(String path, Object obj) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();
	}

	public HashMap<String, Connection> getActiveUsers() {
		return activeUsers;
	}

	public HashMap<String, UserInfo> getRegisteredUsers() {
		return registeredUsers;
	}

	public HashMap<String, ChatRoom> getPublicRooms() {
		return publicRooms;
	}

	public HashMap<String, Object> getStoredFiles() {
		return storedFiles;
	}

}
