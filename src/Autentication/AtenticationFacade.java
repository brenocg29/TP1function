package Autentication;

/**
 * Project pattern for login and registration
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.simple.DeserializationException;

import dao.JsonHandler;
import siteEntities.Comrade;

public class AtenticationFacade {
	private Register R;
	private Logger L;
	private static AtenticationFacade A;
	private  AtenticationFacade(){
	}
	/**
	 * Usado para implementar o metodo singleton
	 * @return an instance of the class
	 */
	public static AtenticationFacade getInstance() {
		if (A == null) {
			A = new AtenticationFacade();
		}
		return A;
	}
	/**
	 * make an user register via register class
	 * @param name name of user
	 * @param UIN unified international number 
	 * @param pass password
	 * @param LastName 
	 * @param username 
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws DeserializationException
	 */
	public boolean MakeRegister(String name, String UIN,String pass, String LastName, String username) throws NoSuchAlgorithmException, IOException, DeserializationException {
		R = Register.getInstance(name, UIN, pass, LastName, username);
		boolean teste = R.SaveNewUser();
		if (teste == false) {
			System.out.println("Algum erro ocorreu, verifique o username");
			Register.deleteInstance();
			R = null;
			return false;
		}
		else {
			System.out.println("Usuário cadastrado com sucesso!");
			Register.deleteInstance();
			R = null;
			return true;
		}
		
	}
	public Register AppendToReg(String name, String UIN,String pass, String LastName, String username,String salt) throws FileNotFoundException, NoSuchAlgorithmException, DeserializationException, IOException {
		R = Register.getInstance(name, UIN, pass, LastName, username,salt);
		return R;
	}
	/**
	 * Log a user using logger class
	 * @param user
	 * @param pass
	 * @return the comrade corresponding to the user
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 * @throws DeserializationException
	 * @throws IOException
	 */
	public Comrade MakeLogin(String user, String pass) throws FileNotFoundException, NoSuchAlgorithmException, DeserializationException, IOException {
		L = Logger.getInstance(user, pass);
		boolean teste = L.CheckUserPass();
		if (teste == false) {
			System.out.println("Login mal sucedido!");
			Logger.deleteInstance();
			L = null;
			return null;
		}
		else {
			System.out.println("Login bem sucedido!");
			JsonHandler J = new JsonHandler("comrade");
			Logger.deleteInstance();
			L = null;
			Comrade C = J.readCFromJson(user);
			return C;
		}
	}
	
}