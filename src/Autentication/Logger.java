package Autentication;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.DeserializationException;

import back.Comrade;
import back.JsonHandler;
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
	/**
	 * Construct the object with parameters
	 * @param user username given by user
	 * @param pass password given by user
	 */
	public Logger(String user, String pass){
		this.user = user;
		this.pass = pass;
	}
	/**
	 * Check data and returns comrade object
	 * @return
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public boolean CheckUserPass() throws FileNotFoundException, DeserializationException, IOException {
		JsonHandler J = new JsonHandler();
		String t = J.readFromJson(user);
		if (t == null) {
			return false;
		}
		else {
			System.out.println(t);
			System.out.println(pass);
			if(t.equals(pass)) {
				System.out.println("ollaaaa");
				return true;
			}
			return false;
		}
		
		
	}
	/**
	 * Handles a situation where user forgot his password
	 */
	public void ForgotPass() {
		
	}
	/**
	 * Handles the situation where user wants to change its password
	 * @param NewPass new password
	 */
	public void changeUserPass(String NewPass) {
		
	}
}
