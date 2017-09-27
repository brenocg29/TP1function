package back;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.json.*;

import Autentication.Register;
/**
 * Class to handle all writing to json
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 */
public class JsonHandler {
	private String pathToFile;
	JSONObject J;
	/**
	 * 
	 * @param Com Write an object to json, the object can be an comrade a page a post or a group
	 * @return result of operation
	 */
	
	public JsonHandler() {
			J = new JSONObject();		
	}
	private static String readFile(String name) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(name));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while(line!=null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean SaveJson(Register Reg) throws JSONException, IOException{
		JSONObject aux = new JSONObject();
		aux.put("Nome",Reg.getName());
		aux.put("LastName", Reg.getLastName());
		aux.put("UIN", Reg.getUIN());
		aux.put("password", Reg.getPass());
		J.put(Reg.getUserName(),aux);		
		FileWriter file = new FileWriter("users.json",true);
		file.write(J.toString());
		file.flush();
		file.close();
		return true;
	}
	public boolean SaveJson(Comrade Com) throws JSONException{
		
		return true;
	}
	public boolean SaveJson(Group G) {
		return true;
	}
	public boolean SaveJson(Page P) {
		return true;
	}
	public boolean SaveJson(Post P) {
		return true;
	}
	/**
	 * 
	 * @param G Read from json, the object can be a comrade page, group or post
	 * @return result of operation
	 */
	public boolean readFromJson(Group G) {
		return true;
	}
	public boolean readFromJson(Comrade Com) {
		return true;
	}
	public boolean readFromJson(Page P) {
		return true;
	}
	public boolean readFromJson(Post P) {
		return true;
	}
}
