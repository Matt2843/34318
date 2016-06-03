package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	private ObjectOutputStream output;
	private ObjectInputStream input;

	private String serverIP;
	private int port;

	private Socket connection;

	public Client(String host, int port) {
		serverIP = host;
		this.port = port;
		try {
			connectToServer();
			configureStreams();
			whileConnected();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void whileConnected() throws IOException {
		String message = "";
		do {
			try {
				message = input.readObject().toString();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (message.equals("DISCONNECTCODE"));
		cleanUp();
	}

	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}

	private void connectToServer() throws IOException {
		connection = new Socket(InetAddress.getByName(serverIP), port);
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

}
