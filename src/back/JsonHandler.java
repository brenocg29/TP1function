package back;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import org.json.simple.DeserializationException;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;
import org.json.simple.parser.JSONParser;

import Autentication.Register;
/**
 * Class to handle all writing to json
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 */
public class JsonHandler {
	private String pathToFile;
	JsonObject J;
	/**
	 * 
	 * @param Com Write an object to json, the object can be an comrade a page a post or a group
	 * @return result of operation
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	
	public JsonHandler() throws FileNotFoundException, DeserializationException, IOException {
			J = (JsonObject) Jsoner.deserialize(new FileReader("users.json"));		
	}
	public boolean SaveJson(Register Reg) throws JSONException, IOException, DeserializationException{
		JsonObject aux = new JsonObject();
		aux.put("Nome",Reg.getName());
		aux.put("LastName", Reg.getLastName());
		aux.put("UIN", Reg.getUIN());
		aux.put("password", Reg.getPass());
		aux.put("salt", Reg.getSalt());
		J.put(Reg.getUserName(),aux);		
		FileWriter file = new FileWriter("users.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
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
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public JsonObject readFromJson(String  RegID) throws FileNotFoundException, DeserializationException, IOException {
		J = (JsonObject) Jsoner.deserialize(new FileReader("users.json"));	
		J = (JsonObject) J.get(RegID);
		if (J == null) return null;
		return J;
	}
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
