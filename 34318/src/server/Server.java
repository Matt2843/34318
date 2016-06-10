package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	public static String serverStatus = "Ready...";

	public static Database db = new Database();
	
	private boolean running = true;
	
	private ServerSocket server;
	private Socket connection;
	private int port;

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
			db.addNewConnection(sessionID, newClient);
			try {
				Thread.sleep(150);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void cleanUp() {
		for(int i = 0; i < db.getActiveUsers().size(); i++) {
			db.getActiveUsers().get(i).stopConnection();
		}
		try {
			db.updateAndSaveDatabase();
			connection.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return running;
	}

}
