package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays; 

public class ClientFTP {
	public static void main(String[] args) throws Exception {
		String fileName = null;

		try {
			fileName = args[0];
		} catch (Exception e) {

			File file = new File("C:/Users/chris/OneDrive/Billeder/text.txt");
			Socket socket = new Socket("localhost", 3332);
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

			outputStream.writeObject(file.getName());

			FileInputStream fileInputStream = new FileInputStream(file);
			byte [] buffer = new byte[100]; //Størrelsen af bufferen
			Integer bytesRead = 0;

			while ((bytesRead = fileInputStream.read(buffer)) > 0) {
				outputStream.writeObject(bytesRead);
				outputStream.writeObject(Arrays.copyOf(buffer, buffer.length));
			}

			inputStream.close();
			outputStream.close();
			System.exit(0);    
		}

	}

}  
