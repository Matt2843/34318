package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server extends Thread {
	private HashMap<String, Connection> activeUsers = new HashMap<String, Connection>();
	
	private ServerSocket server;
	private Socket connection;
	private int port;
	
	private boolean running = true;
	private String serverStatus = "Ready...";

	public Server(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		initializeServer();
		super.run();
	}

	private void initializeServer() {
		try {
			waitForConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
			System.exit(0);
		}
	}

	private void waitForConnection() throws IOException {
		while(running) {
			connection = server.accept();
			String sessionID = SU.generateSessionID(5);
			while(activeUsers.containsKey(sessionID)) {
				sessionID = SU.generateSessionID(5);
			}
			Connection newClient = new Connection(connection, sessionID);
			newClient.start();
			activeUsers.put(sessionID, newClient);
			try {
				Thread.sleep(150);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void cleanUp() {
		try {
			connection.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Connection> getActiveUsers() {
		return activeUsers;
	}

}
