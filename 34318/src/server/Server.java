package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket server;
	private Socket connection;

	private boolean running = false;

	public Server(int port) {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sleepMode();
	}

	private void sleepMode() {
		try {
			waitForConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cleanUp();
			System.exit(0);
		}
	}

	private void waitForConnection() throws IOException {
		while(running) {
			connection = server.accept();
			Connection newClient = new Connection(connection);
			newClient.start();
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
