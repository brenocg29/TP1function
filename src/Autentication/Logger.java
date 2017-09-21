package Autentication;
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
	Logger(String user, String pass){
		this.user = user;
		this.pass = pass;
	}
	/**
	 * Check data and returns comrade object
	 * @return
	 */
	public Comrade CheckUserPass() {
		Comrade X = new Comrade("","","","","");
		return X;
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
