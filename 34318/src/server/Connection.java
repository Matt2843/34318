package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import utility.Message;

public class Connection extends Thread {

	private ObjectInputStream input;
	private ObjectOutputStream output;

	private boolean alive = true;
	private boolean loggedIn = false;

	private String clientIP = null;
	private Socket client = null;
	
	private String sessionID = null;
	private String connectionCheck = "";
	
	private Slave slave = null;
	
	public static ArrayList<String> tasks = new ArrayList<String>();

	public Connection(Socket connection, String sessionID) {
		this.client = connection; this.sessionID = sessionID;
		clientIP = client.getRemoteSocketAddress().toString();
	}

	@Override
	public void run() {
		try {
			Message message;
			configureStreams();
			greetUser();
			slave = new Slave(this);
			slave.start();
			checkConnectivity();
			while(alive) {
				message = (Message) input.readObject();
				System.out.println("FROM CLIENT: " + message.toString());
				connectionCheck = message.getCommand();
				slave.decode(message);
				if(message.getCommand().equals("L103")) {
					alive = false;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Socket or streams closed, running cleanUp.");
		} finally {
			cleanUp();
		}
	}

	private void greetUser() {
		sendMessage("L104", null);
	}
	
	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
	}
	
	private void checkConnectivity() {
		new Thread(new Runnable() {
			public void run() {
				while(alive) {
					sendMessage("Q999", null);
					try {
						int countdown = 10;
						while(!connectionCheck.equals("K999") && countdown > 0) {
							Thread.sleep(1000);
							countdown -= 1;
							sendMessage("Countdown: " + countdown, null);
						}
						if(countdown == 0) {
							alive = false;
							cleanUp();
						}
						sendMessage("Sleeping 1 minute", null);
						Thread.sleep(1000*5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
	
	public void sendMessage(String command, String[] params) {
		Message m = new Message(command, params);
		try {
			output.writeObject(m);
			output.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String command, String[] params, Object object) {
		Message m = new Message(command, params, object);
		try {
			output.writeObject(m);
			output.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void cleanUp() {
		if(alive) {
			sendMessage("K100", null); // ADD PARAMETER MYBE MAYBE NOT?
		}
		Server.setServerStatus("Connection " + sessionID + " timed out.");
		try {
			input.close();
			output.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnectionAlive() {
		return alive;
	}
	
	public void stopConnection() {
		alive = false;
	}

	public String getClientIP() {
		return clientIP;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
