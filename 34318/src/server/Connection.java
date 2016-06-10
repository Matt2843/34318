package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import utility.Message;

public class Connection extends Thread {

	private ObjectInputStream input;
	private ObjectOutputStream output;

	private boolean alive = true;
	private boolean loggedIn = false;

	private String clientIP;
	private Socket client;
	
	private String sessionID;
	
	private Slave slave;
	
	public static ArrayList<String> tasks = new ArrayList<String>();

	public Connection(Socket connection, String sessionID) {
		this.client = connection; this.sessionID = sessionID;
		clientIP = client.getRemoteSocketAddress().toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			Message<String, Object> message;
			configureStreams();
			greetUser();
			slave = new Slave(this);
			slave.start();
			do {
				message = (Message<String, Object>) input.readObject();
				System.out.println("FROM CLIENT: " + message.getString());
				slave.decode(message);
			} while (!message.getString().substring(0, 5).equals("L103") && alive);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	// Identify User Code: L104, maybe add a greetUser handshake method.
	private void greetUser() {
		sendMessage("L104#" + sessionID);
	}
	
	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
	}

	private void cleanUp() {
		sendMessage("K100#Server Closed");
		try {
			input.close();
			output.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		Message<String, Object> m = new Message<String, Object>(message);
		try {
			output.writeObject(m);
			output.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message, Object object) {
		Message<String, Object> m = new Message<String, Object>(message, object);
		try {
			output.writeObject(m);
			output.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isConnectionAlive() {
		return alive;
	}
	
	public void stopConnection() {
		alive = false;
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

}
