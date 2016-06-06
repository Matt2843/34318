package server;

import client.Client;

public class ServerEngine {

	public static void main(String[] args) throws InterruptedException {

		Server s = new Server(1234);
		s.start();
		Thread.sleep(100);
		Client t = new Client("localhost", 1234);
		t.start();
		Thread.sleep(100);
		t.sendMessage("Greetings from client!");
		t.sendMessage("Hello Hello");
		t.sendMessage("L100");
	}

}
