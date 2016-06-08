package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import utility.Message;

public class Client extends Thread {
	private ObjectOutputStream output;
	private ObjectInputStream input;

	private String host;
	private int port;

	private Socket connection;
	
	private String sessionID = "-1";

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

	@SuppressWarnings("unchecked")
	private void whileConnected() throws IOException {
		Message<String, Object> message = null;
		do {
			try {
				message = (Message<String, Object>) input.readObject();
				decodeMessage(message.getString());
				System.out.println("FROM SERVER: " + message.getString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} while (!message.getString().substring(0, 5).equals("K100") || !message.getString().substring(0, 5).equals("K101"));
		cleanUp();
	}

	private void decodeMessage(String message) {
		String msgSplit[] = message.split("#");
		switch (msgSplit[0]) {
		case "L104":
			sessionID = msgSplit[1]; 
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

	public void sendMessage(String msg) {
		String message = msg + "#" + sessionID;
		Message<String, Object> m = new Message<String, Object>(message);
		try {
			output.writeObject(m);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg, Object object) {
		String message = msg + "#" + sessionID;
		Message<String, Object> m = new Message<String, Object>(message, object);
		try {
			output.writeObject(m);
			output.flush();
		} catch (Exception e) {
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
