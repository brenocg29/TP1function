package Security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class Hashing {
	static public String HashString(String pass, byte[] Salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hashedpass = pass.getBytes("UTF-8");
		byte[] aux = new byte[hashedpass.length + Salt.length];
		System.arraycopy(hashedpass, 0, aux, 0, hashedpass.length);
		System.arraycopy(Salt, 0, aux, hashedpass.length, hashedpass.length);
		byte[] hashed = md.digest(aux);
		String hashedStr = DatatypeConverter.printHexBinary(hashed);
	return hashedStr;
	}
}
