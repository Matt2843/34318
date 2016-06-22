package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

import server.Server;
 
public class ClientFTP {
    public static void main(String[] args) throws Exception { 
        File file = new File("C:/Users/Matt/Desktop/Scripts/flaskScript.zip");
        Socket socket = new Socket("localhost", 1234);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
 
        oos.writeObject(file.getName());
 
        FileInputStream fis = new FileInputStream(file);
        byte [] buffer = new byte[1000000];
        Integer bytesRead = 0;
 
        while ((bytesRead = fis.read(buffer)) > 0) {
            oos.writeObject(bytesRead);
            oos.writeObject(Arrays.copyOf(buffer, buffer.length));
        }
 
        oos.close();
        ois.close();
    }
}
//private void uploadFile(String path, String targetRoom){
//	try {	
//		File file = new File(path);
//
//		FileInputStream fileInputStream = new FileInputStream(file);
//		byte [] buffer = new byte[10000]; //Størrelsen af bufferen
//		Integer bytesRead = 0;
//
//		while ((bytesRead = fileInputStream.read(buffer)) > 0) {
//			output.writeObject(bytesRead);
//			output.writeObject(Arrays.copyOf(buffer, buffer.length));
//		}
//
//		input.close();
//		output.close();
//		System.exit(0);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//}
//  
//
//private void downloadFile(String link){
//	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//	FileInputStream fileInputStream = connection.getFileInputStream();
//    try {
//    	output.writeObject(link); //link skal være en sti!
//		output.flush();
//		
//    	byte[] buffer = new byte[10000];
//        FileOutputStream fileOutputStream = new FileOutputStream(link); //dette er hvor det skal gemmes!
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//        Integer bytesRead = 0;
//
//        while ((bytesRead = fileInputStream.read(buffer)) > 0);{
//                byteArray.write(buffer);      
//        }
//
//        bufferedOutputStream.write(byteArray.toByteArray());
//        bufferedOutputStream.flush();
//        bufferedOutputStream.close();
//        fileInputStream.close();
//    } catch (IOException e) {
//    	e.printStackTrace();
//    }
//}
