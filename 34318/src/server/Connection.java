package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import utility.Message;

public class Connection extends Thread {

	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private boolean loggedIn = false;

	private String clientIP = null;
	private Socket client = null;
	
	private String username = null;
	
	private Slave slave = null;
	
	public static ArrayList<String> tasks = new ArrayList<String>();

	public Connection(Socket connection) {
		this.client = connection; //this.sessionID = sessionID;
		slave = new Slave(this);
		slave.start();
		clientIP = client.getRemoteSocketAddress().toString();
	}

	@Override
	public void run() {
		try {
			Message message;
			configureStreams();
			greetUser();
			do {
				message = (Message) input.readObject();
				System.out.println("FROM CLIENT: " + message.toString());
				slave.translate(message);
				if(message.getCommand().equals("X999")) break;
			} while(true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}

	private void greetUser() {
		sendMessage("L104", null);
	}
	
	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
	}
	
	public void sendMessage(String command, String[] params) {
		Message m = new Message(command, params);
		try {
			if(!client.isClosed()) {
				output.writeObject(m);
				output.flush();
			} else {
				System.out.println("SOCKET IS DEAD");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String command, String[] params, Object object) {
		Message m = new Message(command, params, object);
		try {
			if(!client.isClosed()) {
				output.writeObject(m);
				output.flush();
			} else {
				System.out.println("SOCKET IS DEAD");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendFile(String path, String targetRoom) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		
		byte [] buffer = new byte[2000]; //Størrelsen af bufferen
		Integer bytesRead = 0;
		
		while ((bytesRead = fis.read(buffer)) > 0) {
			output.writeObject(bytesRead);
			output.writeObject(Arrays.copyOf(buffer, buffer.length));
			output.flush();
		}
		
		fis.close();
	}
	
	private void cleanUp() {
		System.out.println("[CONNECTION]Cleaning up...");
		if(username != null) Server.db.getActiveUsers().remove(username);
		try {
			slave.stopUpdateUserThread();
			slave.join();
			input.close();
			output.close();
			client.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getClientIP() {
		return clientIP;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
