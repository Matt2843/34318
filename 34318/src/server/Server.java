package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket server;
	private Socket connection;

	private boolean running = true;

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
			newClient.sendMessage("HELLO FROM SERVER");
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
