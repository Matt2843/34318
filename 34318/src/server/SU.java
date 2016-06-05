package server;

import java.util.Random;

public final class SU {
	
	// Prevents instantiation of the pure static class
	private SU() {
	}
	
	public static final String generateSessionID() {
		String keys = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String sessionID = "";
		Random rand = new Random();
		int randomNum = 0;
		for(int i = 0; i < 5; i++) {
			randomNum = rand.nextInt(keys.length());
			sessionID += keys.charAt(randomNum);
		}
		return sessionID;
	}
}
