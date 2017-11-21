package Autentication;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

import org.json.simple.DeserializationException;
import org.json.simple.JsonObject;

import Security.Hashing;
import dao.JsonHandler;
import siteEntities.Comrade;
import siteEntities.Feed;
/**
 * Handles login operation
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 *
 */
public class Logger {
	private String user;
	private String pass;
	private static Logger instance;
	/**
	 * Construct the object with parameters
	 * @param user username given by user
	 * @param pass password given by user
	 */
	private  Logger(String user, String pass){
		this.user = user;
		this.pass = pass;
	}
	public static Logger getInstance(String user, String pass) {
		if (instance == null){
			instance = new Logger(user,pass);
			return instance;
		}
		else return instance;
	}
	/**
	 * Check user data
	 * @return true if user and pass are correct false otherwise
	 * @throws FileNotFoundException
	 * @throws DeserializationException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public boolean CheckUserPass() throws FileNotFoundException, DeserializationException, IOException, NoSuchAlgorithmException {
		//change to return a Comrade Object
		JsonHandler J = new JsonHandler("register");
		Register t = J.readRFromJson(user);
		if (t == null) {
			return false;
		}
		else {
			String salt = t.getSalt();
//			System.out.println("this is " + salt);
			String password = t.getPass(); 
			String hashed = Hashing.HashString(pass, DatatypeConverter.parseHexBinary(salt));
			if(password.equals(hashed)) {
				Register.deleteInstance();
				return true;
			}
			Register.deleteInstance();
			return false;
		}
		
	}
	/**
	 * Handles the situation where user wants to change its password
	 * @param NewPass new password
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws NoSuchAlgorithmException 
	 * @throws FileNotFoundException 
	 */
	public void changeUserPass(String NewPass) throws FileNotFoundException, NoSuchAlgorithmException, DeserializationException, IOException {
		System.out.println("entrei");
		if (CheckUserPass() == true) {
			SecureRandom sr = new SecureRandom();
			byte[] S = new byte[10];
			sr.nextBytes(S);
			String salt = DatatypeConverter.printHexBinary(S);
			String Npass = Hashing.HashString(NewPass, S);
			JsonHandler J = new JsonHandler("register");
			Register t = J.readRFromJson(this.user);
			System.out.println(t.pass);
			t.pass = Npass;
			t.salt = salt;
			System.out.println(t.pass);
			J.ReplaceRegister(t, this.user);
		}
		
	}
	public static void deleteInstance() {
		instance = null;
	}
}
