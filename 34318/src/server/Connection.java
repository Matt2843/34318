package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import utility.Message;

public class Connection extends Thread {

	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private boolean loggedIn = false;

	private String clientIP = null;
	private Socket client = null;
	
	private String username = null;
	
	private Slave slave = null;
	
	public static ArrayList<String> tasks = new ArrayList<String>();

	public Connection(Socket connection) {
		this.client = connection; //this.sessionID = sessionID;
		slave = new Slave(this);
		slave.start();
		clientIP = client.getRemoteSocketAddress().toString();
	}

	@Override
	public void run() {
		try {
			Message message;
			configureStreams();
			greetUser();
			do {
				System.out.println("BLOCKING CONNECTION");
				message = (Message) input.readObject();
				System.out.println("FROM CLIENT: " + message.toString());
				slave.translate(message);
			} while(!message.getCommand().equals("X999"));
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public void sendMessage(String command, String[] params) {
		Message m = new Message(command, params);
		try {
			if(!client.isClosed()) {
				output.writeObject(m);
				output.flush();
			} else {
				System.out.println("SOCKET IS DEAD");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String command, String[] params, Object object) {
		Message m = new Message(command, params, object);
		try {
			if(!client.isClosed()) {
				output.writeObject(m);
				output.flush();
			} else {
				System.out.println("SOCKET IS DEAD");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String command, String[] params, Object object, Object objecttwo) {
		Message m = new Message(command, params, object, objecttwo);
		try {
			if(!client.isClosed()) {
				output.writeObject(m);
			} else {
				System.out.println("SOCKET IS DEAD");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receiveFile() {
		System.out.println("RECEIVING FILE");
		try {
			FileOutputStream fos = new FileOutputStream(new File("C:/Users/Matt/Desktop/Kappa.zip"));
			byte [] buffer = new byte[100];
			Integer bytesRead = 0;
			do {
				bytesRead = (Integer) ((Message)input.readObject()).getObject();
				buffer = (byte[]) ((Message)input.readObject()).getObjectTwo();
				fos.write(buffer, 0, bytesRead);
			} while(bytesRead == 100);
			System.out.println("FILE DONE ");
			fos.close();
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void sendFile(String path, String targetRoom) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		
		byte [] buffer = new byte[20000]; //Størrelsen af bufferen
		Integer bytesRead = 0;
		
		while ((bytesRead = fis.read(buffer)) > 0) {
			output.writeObject(bytesRead);
			output.writeObject(Arrays.copyOf(buffer, buffer.length));
			//output.flush();
		}
		
		//fis.close();
	}
	
	public void saveFile(String path) throws Exception {
		FileOutputStream fos = null;
		byte [] buffer = new byte[20000];
		// 1. Read file name.
		Object o = input.readObject();
		try {
		if (o instanceof String) {
			fos = new FileOutputStream(new File("data/hash2.zip"));
		} 
		}catch(IOException e) {
			e.printStackTrace();
		}
		// 2. Read file to the end.
		Integer bytesRead = 0;
		do {
			o = input.readObject();
			if (!(o instanceof Integer)) {
				System.out.println("Something is wrong");
			}
			bytesRead = (Integer)o;
			o = input.readObject();
			if (!(o instanceof byte[])) {
				System.out.println("Something is wrong");
			}
			buffer = (byte[])o;
			// 3. Write data to output file.
			fos.write(buffer, 0, bytesRead);
		} while (bytesRead == 20000);
		fos.close();
		//ois.close();
		//oos.close();
	}
	
	private void cleanUp() {
		System.out.println("[CONNECTION]Cleaning up...");
		if(username != null) Server.db.getActiveUsers().remove(username);
		try {
			slave.stopUpdateUserThread();
			slave.join();
			input.close();
			output.close();
			client.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Socket getClient() {
		return client;
	}

}
