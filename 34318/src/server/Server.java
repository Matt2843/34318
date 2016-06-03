package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
	private ArrayList<Connection> activeUsers = new ArrayList<Connection>();
	
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
		sleepMode();
		super.run();
	}

	private void sleepMode() {
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
			System.out.println("Waiting for someone to connect ...");
			connection = server.accept();
			Connection newClient = new Connection(connection);
			newClient.start();
			activeUsers.add(newClient);
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

	public ArrayList<Connection> getActiveUsers() {
		return activeUsers;
	}

}
