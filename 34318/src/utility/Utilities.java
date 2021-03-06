package utility;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;


public final class Utilities {
	
	public static final int PUBLIC_CHAT = 0;
	public static final int PRIVATE_CHAT = 1;
	public static final int FILE = 2;

	public static String generateID(int length, int type){ // [:2] = PU, public chat. [:2] = PR, private chat. [:2] = FI, file.
		String keys = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String result = "";
		if(type == PUBLIC_CHAT) {
			result += "PU";
		} else if (type == PRIVATE_CHAT) {
			result += "PR";
		} else if(type == FILE) {
			result += "FI";
		}
		Random rand = new Random();
		for (int i = 0; i<length; i++){
			result+=keys.charAt(rand.nextInt(keys.length()));
		}
		return result;
	}

	public static ImageIcon createResizedImageIcon(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        ImageIcon result = new ImageIcon(bi);
        return result;
    }

	public static String encryptString(String string) {
		String hashKey = null;
		try {
			hashKey = ToHexString(Utilities.Hashkey(string));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashKey;
	}

	// Generate the hashkey
	private static byte[] Hashkey(String string) throws Exception {
		java.security.MessageDigest hash = java.security.MessageDigest.getInstance("SHA-1");
		hash.reset();
		hash.update(string.getBytes());
		return hash.digest();
	}
	// Compute the hexadecimal encryptet password
	private static String ToHexString(byte[] buffer) {
		int v;
		StringBuffer stringBuffer = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			v = buffer[i] & 0xff;
			if (v < 16) {
				stringBuffer.append('0');
			}
			stringBuffer.append(Integer.toHexString(v));
		}
		return stringBuffer.toString();
	}
	
	public static String[] setParams(int length, String... p) {
		String[] params = new String[length];
		for (int i = 0; i < length; i++) {
			params[i] = p[i];
		}
		return params;
	}
}

