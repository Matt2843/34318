package server;

import client.Client;

public class ServerEngine {
	
	public static void main(String[] args) {
		
		Server s = new Server(1234);
		s.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Client t = new Client("localhost", 1234);
		t.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		s.getActiveUsers().get(0).sendMessage("Hello there");
		
	}

}
