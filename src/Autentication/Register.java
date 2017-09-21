package Autentication;
import back.JsonHandler;
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
	//Todo hash pass
	Register(String name, String UIN, String pass, String LastName, String username){
		this.userName = username;
		this.pass = pass;
		this.Name = name;
		this.LastName = LastName;
	}
	private boolean CheckUsername(){
		//todo make check if username exist
		return true;
	}
	private void hashPass() {
		//todo Hash password with SHA3
	}
	public boolean SaveNewUser() {	
		if (CheckUsername()!= true) {
			hashPass();
			//todo call jsonhandler to save user
			return true;
		}
		return false;
	}
}
