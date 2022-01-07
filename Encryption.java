package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	
	public static String getEncrypt(String text) throws NoSuchAlgorithmException {
		
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(text.getBytes());
			byte[] bytes = m.digest();
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}	
	       	return s.toString();
	}
}
