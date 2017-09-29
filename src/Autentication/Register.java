package Autentication;
import back.JsonHandler;
import Security.Hashing;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONException;
import org.json.simple.DeserializationException;

import back.Comrade;
/**
 * Class to handle registration process
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 */
public class Register {
	
	private String userName;
	//todo: check on json if user name is in use
	private String pass;
	private String UIN;
	private String Name;
	private String LastName;
	private String salt;
	JsonHandler J;
	//Todo hash pass
	public Register(String name, String UIN, String pass, String LastName, String username) throws FileNotFoundException, DeserializationException, IOException, NoSuchAlgorithmException{
		J = new JsonHandler();
		this.userName = username;
		this.UIN = UIN;
		this.Name = name;
		this.LastName = LastName;
		SecureRandom sr = new SecureRandom();
		byte[] S = new byte[10];
		sr.nextBytes(S);
		this.salt = DatatypeConverter.printHexBinary(S);
		System.out.println(S.toString());
		this.pass = Hashing.HashString(pass, S);
		J = new JsonHandler();
	}
	public String getUserName() {
		return userName;
	}
	public String getPass() {
		return pass;
	}
	
	public String getUIN() {
		return UIN;
	}
	
	public String getName() {
		return Name;
		}
	
	public String getSalt() {
		return salt;
	}
	public JsonHandler getJ() {
		return J;
	}
	public String getLastName() {
		return LastName;
		}
	private boolean CheckUsername(){
		return true;
	}
	private void hashPass() {
		//todo Hash password with SHA3
	}
	public boolean SaveNewUser() throws IOException, DeserializationException {	
		try {
			J.SaveJson(this);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (CheckUsername()!= true) {
			hashPass();
			//todo call jsonhandler to save user
			return true;
		}
		return false;
	}
}
