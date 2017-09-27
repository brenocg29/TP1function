package Autentication;
import back.JsonHandler;

import java.io.IOException;

import org.json.JSONException;

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
	JsonHandler J;
	//Todo hash pass
	public Register(String name, String UIN, String pass, String LastName, String username){
		J = new JsonHandler();
		this.userName = username;
		this.UIN = UIN;
		this.pass = pass;
		this.Name = name;
		this.LastName = LastName;
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
	
	public String getLastName() {
		return LastName;
		}
	private boolean CheckUsername(){
		return true;
	}
	private void hashPass() {
		//todo Hash password with SHA3
	}
	public boolean SaveNewUser() throws IOException {	
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
