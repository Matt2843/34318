package client;
import java.io.*;
import java.net.*;
import java.util.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays; 

public class ClientFTP {
	public static void main(String[] args) throws Exception {

		try {
			
		} catch (Exception e) {

			File file = new File("C:/Users/chris/OneDrive/Billeder/text.txt");
			Socket socket = new Socket("localhost", 3332);
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

			outputStream.writeObject(file.getName());
			outputStream.flush();
			
			FileInputStream fileInputStream = new FileInputStream(file);
			byte [] buffer = new byte[10000]; //Størrelsen af bufferen
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
private void uploadFile(String path, String targetRoom){
	try {	
		File file = new File(path);
		
		FileInputStream fileInputStream = new FileInputStream(file);
		byte [] buffer = new byte[10000]; //Størrelsen af bufferen
		Integer bytesRead = 0;

		while ((bytesRead = fileInputStream.read(buffer)) > 0) {
			output.writeObject(bytesRead);
			output.writeObject(Arrays.copyOf(buffer, buffer.length));
		}

		input.close();
		output.close();
		System.exit(0);
	} catch (Exception e) {
		 e.printStackTrace();
}
}
}  

private void downloadFile(String link){
	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
	InputStream inputStream = connection.getInputStream();
    try {
    	byte[] buffer = new byte[10000];
        FileOutputStream fileOutputStream = new FileOutputStream(link); //Her skal link være en sti!
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        Integer bytesRead = 0;

        while ((bytesRead = inputStream.read(buffer)) > 0);{
                byteArray.write(buffer);      
        }

        bufferedOutputStream.write(byteArray.toByteArray());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        inputStream.close();
    } catch (IOException e) {
    	e.printStackTrace();
    }
}
