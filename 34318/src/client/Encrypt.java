package client;

public final class Encrypt {
	private static int v;
	private static String hashKey;
	public static void encryptString(String string) {
		try {
			hashKey = ToHexString(Encrypt.Hashkey(string));
			System.out.println(hashKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		StringBuffer stringBuffer = new StringBuffer(buffer.length * 2);
		System.out.println(buffer);
		for (int i = 0; i < buffer.length; i++) {
			v = buffer[i] & 0xff;
			if (v < 16) {
				stringBuffer.append('0');
			}
			stringBuffer.append(Integer.toHexString(v));
		}
		return stringBuffer.toString();
	}
	public static void main(String[] args){
		String hans = "hansi";
		encryptString(hans);
	}
}
// DA39A3EE5E6B4B0D3255BFEF95601890AFD80709