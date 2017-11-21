package Autentication;
import siteEntities.Comrade;
import Security.Hashing;
import dao.JsonHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONException;
import org.json.simple.DeserializationException;
import org.json.simple.JsonObject;
/**
 * Class to handle registration process
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 */
public class Register {
	
	private String userName;
	//todo: check on json if user name is in use
	String pass;
	private String UIN;
	private String Name;
	private String LastName;
	String salt;
	JsonHandler J;
	private static Register instance;
	/**
	 * Constructor for new object
	 * @param name
	 * @param UIN
	 * @param pass
	 * @param LastName
	 * @param username
	 * @throws FileNotFoundException
	 * @throws DeserializationException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	private Register(String name, String UIN, String pass, String LastName, String username) throws FileNotFoundException, DeserializationException, IOException, NoSuchAlgorithmException{
		J = new JsonHandler("register");
		this.userName = username;
		this.UIN = UIN;
		this.Name = name;
		this.LastName = LastName;
		SecureRandom sr = new SecureRandom();
		byte[] S = new byte[10];
		sr.nextBytes(S);
		this.salt = DatatypeConverter.printHexBinary(S);
		this.pass = Hashing.HashString(pass, S);
	}
	/**
	 * Constructor for loaded object
	 * @param name
	 * @param UIN
	 * @param pass
	 * @param LastName
	 * @param username
	 * @param salt
	 */
	private  Register(String name, String UIN, String pass, String LastName, String username,String salt) {
		this.userName = username;
		this.UIN = UIN;
		this.Name = name;
		this.LastName = LastName;
		this.pass = pass;
		this.salt = salt;
	}
	/**
	 * get an instance of new object
	 * @param name
	 * @param UIN
	 * @param pass
	 * @param LastName
	 * @param username
	 * @param salt
	 * @return
	 */
	public static Register getInstance(String name, String UIN, String pass, String LastName, String username,String salt) {
		if(instance == null) {
			instance = new Register(name,UIN,pass,LastName,username,salt);
			return instance;
		}
		else return instance;
	}
	/** 
	 * get an instance of loaded object
	 * @param name
	 * @param UIN
	 * @param pass
	 * @param LastName
	 * @param username
	 * @return
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 * @throws DeserializationException
	 * @throws IOException
	 */
	public static Register getInstance(String name, String UIN, String pass, String LastName, String username) throws FileNotFoundException, NoSuchAlgorithmException, DeserializationException, IOException {
		if(instance == null) {
			instance = new Register(name,UIN,pass,LastName,username);
			return instance;
		}
		else return instance;
	
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
	/**
	 * Check if username is already in use
	 * @return
	 * @throws FileNotFoundException
	 * @throws DeserializationException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	private boolean CheckUsername() throws FileNotFoundException, DeserializationException, IOException, NoSuchAlgorithmException{
		JsonHandler auxCheck = new JsonHandler("register");
		
		if(auxCheck.readRFromJson(this.getUserName()) != null) {
			System.out.println("escolha outro Username");
			return false;
		}
		return true;
	}
	/**
	 * saves a new registered user
	 * @return
	 * @throws IOException
	 * @throws DeserializationException
	 * @throws NoSuchAlgorithmException
	 */
	public boolean SaveNewUser() throws IOException, DeserializationException, NoSuchAlgorithmException {	
		if (CheckUsername()!= true) {
			return false;
		}
		try {
			System.out.println("oal");
			J.SaveJson(this);
			Comrade Com = new Comrade(this.Name,this.LastName,this.userName);
			Com.SaveComrade();
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static void deleteInstance() {
		instance = null;
	}
}
