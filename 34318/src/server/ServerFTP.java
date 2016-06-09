package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFTP extends Thread {
	public static final int BUFFER_SIZE = 10000;

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(3332);

			while (true) {
				Socket s = serverSocket.accept();
				uploadFile(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void uploadFile(Socket socket) throws Exception {
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		FileOutputStream fileOutputStream = null;
		byte [] buffer = new byte[BUFFER_SIZE];

		// 1. Read file name.
		Object o = inputStream.readObject();
		if (o instanceof String) {
			fileOutputStream = new FileOutputStream(new File("D:/text.txt"));
		} else {
			throwException("Something is wrong");
		}

		// 2. Read file to the end.
		Integer bytesRead = 0;
		while (bytesRead == BUFFER_SIZE){
			o = inputStream.readObject();
			if (!(o instanceof Integer)) {
				throwException("Something is wrong");
			}
			bytesRead = (Integer)o;
			o = inputStream.readObject();
			if (!(o instanceof byte[])) {
				throwException("Something is wrong");
			}

			buffer = (byte[])o;

			// 3. Write data to output file.
			fileOutputStream.write(buffer, 0, bytesRead);
		} 
		
		System.out.println("File transfer success");
		fileOutputStream.close();
		inputStream.close();
		outputStream.close();
	}

	public static void throwException(String message) throws Exception {
		throw new Exception(message);
	}

	public static void main(String[] args) {
		new ServerFTP().start();
	}
}  
