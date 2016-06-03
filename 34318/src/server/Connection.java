package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {

	private ObjectInputStream input;
	private ObjectOutputStream output;

	private boolean alive = true;

	private String clientIP;
	private Socket client;

	public Connection(Socket connection) {
		this.client = connection;
		clientIP = client.getRemoteSocketAddress().toString();
	}

	@Override
	public void run() {
		super.run();
		try {
			configureStreams();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}

	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
	}

	private void cleanUp() {
		try {
			input.close();
			output.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		System.out.println("FROM SERVER: Attemptring to send message " + message);
		try {
			output.writeObject(message);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isClientAlive() {
		return alive;
	}

	public String getClientIP() {
		return clientIP;
	}

}
