package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import chat.ChatRoom;

@SuppressWarnings("unchecked")
public class Database {
	private HashMap<String, Connection> activeUsers = new HashMap<String, Connection>();
	
	private HashMap<String, String> registeredUsers = null;
	private HashMap<String, ChatRoom> publicRooms = null;

	public Database() {
		String path = "data/registeredUsers.db";
		try {
			registeredUsers = new File(path).exists() ? (HashMap<String,String>) readFileToObject(path) : new HashMap<String, String>();
			path = "data/publicRooms.db";
			publicRooms = new File(path).exists() ? (HashMap<String, ChatRoom>) readFileToObject(path) : new HashMap<String, ChatRoom>();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}	
	}

	public void updateAndSaveDatabase() {
		try {
			saveObjectToFile("data/registeredUsers.db", registeredUsers);
			saveObjectToFile("data/publicRooms.db", publicRooms);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Object readFileToObject(String path) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("data/registeredUsers.db");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object target = ois.readObject();
		ois.close();
		fis.close();
		return target;
	}

	private boolean saveObjectToFile(String path, Object obj) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();
		fos.close();
		return true;
	}

	public HashMap<String, Connection> getActiveUsers() {
		return activeUsers;
	}

	public HashMap<String, String> getRegisteredUsers() {
		return registeredUsers;
	}

	public HashMap<String, ChatRoom> getPublicRooms() {
		return publicRooms;
	}

}
