package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread {
	private ObjectOutputStream output;
	private ObjectInputStream input;

	private String host;
	private int port;

	private Socket connection;
	
	private String sessionID = ""; // Think of smart inital value.

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			connectToServer();
			configureStreams();
			whileConnected();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.run();
	}

	private void whileConnected() throws IOException {
		String message = "";
		do {
			try {
				message = input.readObject().toString();
				decodeMessage(message);
				System.out.println("FROM SERVER: " + message);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} while (!message.substring(0, 5).equals("K100") || !message.substring(0, 5).equals("K101"));
		cleanUp();
	}

	private void decodeMessage(String message) {
		String msgSplit[] = message.split("#");
		switch (msgSplit[0]) {
		case "L104":
			sessionID = msgSplit[1]; 
			System.out.println(sessionID);
			break;
		default:
			break;
		}
		
	}

	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}

	private void connectToServer() throws IOException {
		connection = new Socket(InetAddress.getByName(host), port);
	}

	public void sendMessage(String message) {
		try {
			output.writeObject(message);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void cleanUp() {
		try {
			output.close();
			input.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSessionID() {
		return sessionID;
	}

}
