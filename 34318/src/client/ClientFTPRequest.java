package client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientFTPRequest {
	public static void main(String[] args) throws Exception {
		int bytesRead;
		int current = 0;
		
		Socket s = new Socket("localhost", 3332);
		ObjectOutputStream outputStream = (ObjectOutputStream) s.getOutputStream();
		


		//Receive file
		byte[] byteArray = new byte[100];
		ObjectInputStream inputStream = (ObjectInputStream) s.getInputStream();
		FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/chris/OneDrive/Billeder/data.txt");
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		bytesRead = inputStream.read(byteArray, 0, byteArray.length);
		current = bytesRead;

		do {bytesRead =	inputStream.read(byteArray,current, (byteArray.length - current));
			if (bytesRead >= 0) {
				current += bytesRead;
			}
		} while (bytesRead > -1);

		bufferedOutputStream.write(byteArray, 0, current);
		bufferedOutputStream.flush();
		outputStream.flush();

		bufferedOutputStream.close();
		s.close();
	}
}

