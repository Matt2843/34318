package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
//	private String connectionCheck = "";
	
	private Slave slave = null;
	
	public static ArrayList<String> tasks = new ArrayList<String>();

	public Connection(Socket connection, String sessionID) {
		this.client = connection; this.sessionID = sessionID;
		clientIP = client.getRemoteSocketAddress().toString();
	}

	@Override
	public void run() {
		try {
			//Message<String, Object> message;
			Message message;
			configureStreams();
			greetUser();
			slave = new Slave(this);
			slave.start();
//			checkConnectivity();
			while(alive) {
				//message = (Message<String, Object>) input.readObject();
				message = (Message) input.readObject();
				System.out.println("FROM CLIENT: " + message.toString());
//				connectionCheck = message.getCommand();
				slave.decode(message);
//				if(message.getString().substring(6, 10).equals("L103")) {
//					alive = false;
//					break;
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	// Identify User Code: L104, maybe add a greetUser handshake method.
//	private void greetUser() {
//		sendMessage("L104#" + sessionID);
//	}
	
	private void greetUser() {
		sendMessage("L104", null);
	}
	
	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
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

//	public void sendMessage(String message) {
//		Message<String, Object> m = new Message<String, Object>(message);
//		try {
//			output.writeObject(m);
//			output.flush();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void sendMessage(String message, Object object) {
//		Message<String, Object> m = new Message<String, Object>(message, object);
//		try {
//			output.writeObject(m);
//			output.flush();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	private void checkConnectivity() {
//		new Thread(new Runnable() {
//			public void run() {
////				while(true) {
////					sendMessage("Q999");
////					
////					try {
////						int countdown = 5;
////						while(!connectionCheck.substring(6,10).equals("K999") && countdown > 0) {
////							sendMessage("Countdown: " + countdown);
////							countdown--;
////							Thread.sleep(1000);
////						}
////						if(countdown == 0) {
////							sendMessage("K100#Timed Out");
////							cleanUp();
////							break;
////						} else {
////							countdown = 60;
////							sendMessage("Sleeping one minute");
////							Thread.sleep(1000*5);
////						}
////						sendMessage("KAPPA");
////					} catch(Exception e) {
////						e.printStackTrace();
////					}
////					
////				}
//				while(true) {
//					sendMessage(sessionID, "Q999", null);
//					try {
//						Thread.sleep(1000*5);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				
//			}
//		}).start();
//	}
	
	private void cleanUp() {
		if(alive) {
			sendMessage("K100", null); // ADD PARAMETER?
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
