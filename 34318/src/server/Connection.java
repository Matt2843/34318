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
	
	private String sessionID = "";

	public Connection(Socket connection, String sessionID) {
		this.client = connection; this.sessionID = sessionID;
		clientIP = client.getRemoteSocketAddress().toString();
	}

	@Override
	public void run() {
		try {
			String message = "";
			configureStreams();
			sendMessage("L104#" + sessionID); // Identify User Code: L104, maybe add a greetUser handshake method.
			do {
				message = (String) input.readObject();
				System.out.println(message);
			} while (!message.equals("DISCONNECTFLAG"));
		} catch (Exception e) {
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
