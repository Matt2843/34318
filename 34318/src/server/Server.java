package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import server.gui.ServerWindow;

public class Server extends Thread {
	private static String serverStatus = null;

	public static Database db;
	
	private boolean running = true;
	private boolean ready = false;
	
	private ServerSocket server;
	private Socket connection;
	private int port;

	public Server(int port) {
		this.port = port;
		db = new Database();
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
		ready = true;
		while(running) {
			setServerStatus("Waiting for someone to connect ...");
			connection = server.accept();
			Connection newClient = new Connection(connection);
			newClient.start();
			//db.addNewConnection(newClient);
			setServerStatus("New client " + newClient.getClientIP() + " connected.");
			//db.addNewConnection(sessionID, newClient);
			//setServerStatus("New client " + sessionID + " connected." + db.getActiveUsers().keySet());
			try {
				Thread.sleep(150);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void cleanUp() {
		try {
			db.updateAndSaveDatabase();
			connection.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setServerStatus(String status) {
		String timestamp = "[" + new Date().toString().substring(11, 19) + "] ";
		serverStatus = timestamp + status;
		ServerWindow.serverLog.append(serverStatus + "\n");
	}

	public boolean isRunning() {
		return running;
	}

	public String getServerStatus() {
		return serverStatus;
	}

	public boolean isReady() {
		return ready;
	}

}
