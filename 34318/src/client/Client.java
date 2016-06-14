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
	
	//private String sessionID = "-1";
	private String status = "";

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
			System.out.println("Lost connection to server for some reason, .. implement reestablish connection?");
		}
		super.run();
	}

	private void whileConnected() throws IOException {
		Message message = null;
		while(true) {
			try {
				message = (Message) input.readObject();
				System.out.println("FROM SERVER: " + message.toString());
				status = message.getCommand();
			} catch (ClassNotFoundException e) {
				System.out.println("Couldn't cast message to the proper format");
			}
			if(message.getCommand().equals("K100")) break;
			if(message.getCommand().equals("Q999")) sendMessage("K999", null);
		}
		cleanUp();
	}

	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}

	private void connectToServer() throws IOException {
		connection = new Socket(InetAddress.getByName(host), port);
	}

	public void sendMessage(String command, String[] params) {
		Message m = new Message(command, params);
		try {
			output.writeObject(m);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String command, String[] params, Object object) {
		Message m = new Message(command, params, object);
		try {
			output.writeObject(m);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanUp() {
		//sendMessage("L103", null); // ADD PARAMS?
		System.out.println("Closing connection.");
		try {
			output.close();
			input.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public String getSessionID() {
//		return sessionID;
//	}
	
	public void setStatus(String s) {
		status = s;
	}

	public String getStatus() {
		return status;
	}

}
