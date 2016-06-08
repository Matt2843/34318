package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	public static String serverStatus = "Ready...";

	public static Database db = new Database();
	
	private ServerSocket server;
	private Socket connection;
	private int port;
	
	private boolean running = true;

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
	}

	private void initializeServer() {
		try {
			db.updateAndSaveDatabase();
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
			while(db.getActiveUsers().containsKey(sessionID)) {
				sessionID = SU.generateSessionID(5);
			}
			Connection newClient = new Connection(connection, sessionID);
			newClient.start();
			db.getActiveUsers().put(sessionID, newClient);
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

}
