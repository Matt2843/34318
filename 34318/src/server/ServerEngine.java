package server;

import client.Client;

public class ServerEngine {

	public static void main(String[] args) throws InterruptedException {

		Server s = new Server(1234);
		s.start();
		Thread.sleep(100);
		Client t = new Client("localhost", 1234);
		t.start();
		Thread.sleep(200);
		t.sendMessage("L101#MATT#1234");
		Thread.sleep(100);
		Client t1 = new Client("localhost", 1234);
		t1.start();
		Thread.sleep(200);
		t1.sendMessage("L101#ALSTED#1234");
		Thread.sleep(100);
		Server.db.updateAndSaveDatabase();
	}

}
