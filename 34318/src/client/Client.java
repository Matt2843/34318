package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

import utility.Message;

public class Client extends Thread {
	private ObjectOutputStream output;
	private ObjectInputStream input;

	private String host;
	private int port;

	private Socket connection;
	private boolean running = false;
	
	private UserInfo profile;
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
			e.printStackTrace();
			//System.out.println("Lost connection to server for some reason, .. implement reestablish connection?");
		}
	}

	private void setupSlave() {
		slave = new ClientSlave();
		slave.start();
	}

	private void whileConnected() throws IOException {
		Message message = null;
		try {
			do {
				message = (Message) input.readObject();
				slave.translate(message);
				System.out.println("FROM SERVER: " + message.toString());
				if(message.getCommand().equals("K100")) break; // Kick Command
			} while(running);
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("Something stopped the client.");
		} finally {
			cleanUp();
		}
	}

	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}

	private void connectToServer() throws IOException {
		connection = new Socket(InetAddress.getByName(host), port);
	}
	
	@Override
	public synchronized void start() {
		running = true;
		super.start();
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

	public void sendMessage(String command, String[] params, Object object) {
		Message m = new Message(command, params, object);
		try {
			output.writeObject(m);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String command, String[] params, Object object, Object objecttwo) {
		Message m = new Message(command, params, object, objecttwo);
		try {
			if(!connection.isClosed()) {
				output.writeObject(m);
			} else {
				System.out.println("SOCKET IS DEAD");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendFile(String path, String targetRoom) {
		System.out.println(path);
		File file = new File(path);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			byte [] buffer = new byte[100]; //Størrelsen af bufferen
			Integer bytesRead = 0;
			String[] params = {targetRoom};
			sendMessage("F100", params);
			while ((bytesRead = fis.read(buffer)) > 0) {
				sendMessage("F101", null, bytesRead, Arrays.copyOf(buffer, buffer.length));
			}
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	private void saveFile(String path) throws Exception {
//		FileOutputStream fos = null;
//		byte [] buffer = new byte[20000];
//		// 1. Read file name.
//		Object o = input.readObject();
//		try {
//		if (o instanceof String) {
//			fos = new FileOutputStream(new File("C:/Users/chris/Desktop/hash2.zip"));
//		} 
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		// 2. Read file to the end.
//		Integer bytesRead = 0;
//		do {
//			o = input.readObject();
//			if (!(o instanceof Integer)) {
//				System.out.println("Something is wrong");
//			}
//			bytesRead = (Integer)o;
//			o = input.readObject();
//			if (!(o instanceof byte[])) {
//				System.out.println("Something is wrong");
//			}
//			buffer = (byte[])o;
//			// 3. Write data to output file.
//			fos.write(buffer, 0, bytesRead);
//		} while (bytesRead == 20000);
//		fos.close();
//		//ois.close();
//		//oos.close();
//	}

	private void cleanUp() {
		System.out.println("[CLIENT]Closing connection...");
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

	public UserInfo getProfile() {
		return profile;
	}

	public void setProfile(UserInfo profile) {
		this.profile = profile;
	}

}
