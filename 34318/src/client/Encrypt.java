package client;

public final class Encrypt {
	public static void encryptString(String string) {
		try {
			String hashKey = byteArrayToHexString(Encrypt.computeHashkey(string));
			System.out.println(hashKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static byte[] computeHashkey(String string) throws Exception {
		java.security.MessageDigest algorithm = java.security.MessageDigest.getInstance("SHA-1");
		algorithm.reset();
		algorithm.update(string.getBytes());
		return algorithm.digest();
	}

	private static String byteArrayToHexString(byte[] buffer) {
		StringBuffer stringBuffer = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			int v = buffer[i] & 0xaa;
			if (v < 16) {
				stringBuffer.append('0');
			}
			stringBuffer.append(Integer.toHexString(v));
		}
		return stringBuffer.toString().toUpperCase();
	}
}
