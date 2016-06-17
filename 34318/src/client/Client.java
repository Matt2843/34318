package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import utility.Message;

public class Client extends Thread {
	private ObjectOutputStream output;
	private ObjectInputStream input;

	private String host;
	private int port;

	private Socket connection;
	private boolean running = false;
	
	private ClientSlave slave;
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			connectToServer();
			configureStreams();
			setupSlave();
			whileConnected();
		} catch (IOException e) {
			System.out.println("Lost connection to server for some reason, .. implement reestablish connection?");
		}
		super.run();
	}

	private void setupSlave() {
		slave = new ClientSlave(this);
		slave.start();
	}

	private void whileConnected() throws IOException {
		Message message = null;
		while(running) {
			try {
				message = (Message) input.readObject();
				System.out.println("FROM SERVER: " + message.toString());
				slave.translate(message);
			} catch (ClassNotFoundException e) {
				System.out.println("Couldn't cast message to the proper format");
			}
			if(message.getCommand().equals("K100")) break;
			if(message.getCommand().equals("Q999")) sendMessage("K999", null);
		}
		cleanUp();
	}

	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}

	private void connectToServer() throws IOException {
		connection = new Socket(InetAddress.getByName(host), port);
	}

	public void sendMessage(String command, String[] params) {
		Message m = new Message(command, params);
		try {
			output.writeObject(m);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized void start() {
		running = true;
		super.start();
	}

	public void sendMessage(String command, String[] params, Object object) {
		Message m = new Message(command, params, object);
		try {
			output.writeObject(m);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanUp() {
		System.out.println("Closing connection.");
		try {
			slave.join();
			output.close();
			input.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return running;
	}

}
