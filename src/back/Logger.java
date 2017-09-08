package back;
import back.Comrade;
import back.JsonHandler;
public class Logger {
	private String user;
	private String pass;
	Logger(String user, String pass){
		this.user = user;
		this.pass = pass;
	}
	public Comrade CheckUserPass() {
		Comrade X = new Comrade("","","","","");
		return X;
	}
	public void ForgotPass() {
		
	}
	public void changeUserPass() {
		
	}
}
