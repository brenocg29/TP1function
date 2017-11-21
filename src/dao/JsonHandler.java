package dao;
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
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.json.JSONException;
import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import Autentication.AtenticationFacade;
import Autentication.Register;
import siteEntities.Comrade;
import siteEntities.Feed;
import siteEntities.Group;
import siteEntities.Page;
import siteEntities.Post;
/**
 * Class to handle all writing to json
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 */
public class JsonHandler {
	private String pathToFile;
	JsonObject J;
	
	public JsonHandler(String type) throws FileNotFoundException, DeserializationException, IOException {
		if (type.equals("register")) {	
			J = (JsonObject) Jsoner.deserialize(new FileReader("users.json"));
				pathToFile = "users.json";
				}
		if (type.equals("comrade")) {
			J = (JsonObject) Jsoner.deserialize(new FileReader("comrade.json"));
			pathToFile = "comrade.json";
			}
		if(type.equals("feed")) {
			J = (JsonObject) Jsoner.deserialize(new FileReader("feed.json"));
			pathToFile = "feed.json";
		}
		if(type.equals("group")) {
			J = (JsonObject) Jsoner.deserialize(new FileReader("groups.json"));
			pathToFile = "groups.json";
		}
		if(type.equals("page")) {
			J = (JsonObject) Jsoner.deserialize(new FileReader("pages.json"));
			pathToFile = "pages.json";
		}
	}	
	/**
	 * 
	 * @param Com Write an object to json, the object can be an comrade a page a post or a group
	 * @return result of operation
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
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
	public boolean SaveJson(Comrade Com) throws JSONException, IOException{
		JsonObject aux = new JsonObject();
		aux.put("Nome",Com.getName());
		aux.put("LastName", Com.getLastName());
		aux.put("photoPath",Com.getPhotoPath());
		aux.put("birthday",Com.getBirthday());
		aux.put("age", Com.getAge());
		aux.put("comrade", Com.getComrades());
		aux.put("pending", Com.getPendingComrades());
		aux.put("groups", Com.getGroups());
		aux.put("pages", Com.getPages());
		aux.put("messages", Com.getMessages());
		J.put(Com.getUserName(),aux);		
		FileWriter file = new FileWriter("comrade.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
		return true;
	}
	public boolean SaveJson(Group G) throws IOException {
		JsonObject aux = new JsonObject();
		aux.put("name", G.getName());
		aux.put("admin",G.getAdmin());
		aux.put("members",G.getMembers());
		if (J.containsKey(G.getGroupId())) {
			return false;
		}
		J.put(G.getGroupId(), aux);
		FileWriter file = new FileWriter("groups.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
		return true;
	}
	public boolean SaveJson(Page P) throws IOException {
		JsonObject aux = new JsonObject();
		aux.put("name", P.getName());
		aux.put("owner",P.getOwner());
		aux.put("followers",P.getFollowers());
		if(J.containsKey(P.getPageId())) {
			return false;
		}
		J.put(P.getPageId(), aux);
		FileWriter file = new FileWriter("pages.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
		return true;
	}
	public boolean SaveJson(Feed F) throws IOException {
		JsonObject aux = new JsonObject();
		JsonArray JA = new JsonArray();
		aux.put("posts", JA);
		aux.put("date", Integer.toString(F.getDate()));
		aux.put("ppage", Integer.toString(F.getPPage()));
		aux.put("cont", F.getcont());
		J.put(F.getFeedId(),aux);		
		FileWriter file = new FileWriter("feed.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
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
	public Comrade readCFromJson(String  RegID) throws FileNotFoundException, DeserializationException, IOException {
		J = (JsonObject) Jsoner.deserialize(new FileReader(pathToFile));	
		JsonObject ComObj = (JsonObject) J.get(RegID);
		if (ComObj == null) return null;
		System.out.println("vim parar aki");
		Comrade C = new Comrade(ComObj.get("Nome").toString(),ComObj.get("LastName").toString(),RegID,(JsonArray)ComObj.get("comrade"),(JsonArray)ComObj.get("pending"),
				(JsonArray)ComObj.get("pages"),(JsonArray)ComObj.get("groups"),(JsonArray)ComObj.get("messages"),ComObj.getString("birthday"),ComObj.getString("photoPath"),ComObj.getString("age"));
		return C;
	}
	public Register readRFromJson(String  RegID) throws FileNotFoundException, DeserializationException, IOException, NoSuchAlgorithmException {
		J = (JsonObject) Jsoner.deserialize(new FileReader(pathToFile));	
		JsonObject ComObj = (JsonObject) J.get(RegID);
		if (ComObj == null) return null;
		Register R = AtenticationFacade.getInstance().AppendToReg(ComObj.get("Nome").toString(),ComObj.get("UIN").toString(),ComObj.get("password").toString(),ComObj.get("LastName").toString(),RegID,ComObj.get("salt").toString());
		return R;
	}
	public Feed readFFromJson(String  RegID) throws FileNotFoundException, DeserializationException, IOException {
		J = (JsonObject) Jsoner.deserialize(new FileReader(pathToFile));	
		JsonObject ComObj = (JsonObject) J.get(RegID);
		if (J == null) return null;
		Feed F = new Feed(ComObj.getInteger("date"),
						  ComObj.getInteger("ppage"),
						  RegID,
						  (JsonArray)ComObj.get("posts"),
						  ComObj.getInteger("cont"));
		return F;
	}
	public Group readGFromJson(String GId) throws IOException, DeserializationException {
		J = (JsonObject) Jsoner.deserialize(new FileReader(pathToFile));	
		J = (JsonObject) J.get(GId);
		if (J == null) {
			return null;
		}
		Group G = new Group(J.get("admin").toString(),J.get("name").toString(), GId,(JsonArray)J.get("members"));
		return G;
	}
	public boolean readFromJson(Comrade Com) {
		return true;
	}
	public Page readPFromJson(String PId) throws FileNotFoundException, DeserializationException, IOException {
		J = (JsonObject) Jsoner.deserialize(new FileReader(pathToFile));	
		J = (JsonObject) J.get(PId);
		if (J == null) return null;
		Page P = new Page(J.get("name").toString(),J.get("owner").toString(),(JsonArray)J.get("followers"),PId);
		return P;
	}
	public boolean readFromJson(Post P) {
		return true;
	}
	/**
	 * Replace an object that already exists in memory
	 * @param Reg new object 
	 * @param key of of the object to be replaced
	 * @return
	 * @throws FileNotFoundException
	 * @throws DeserializationException
	 * @throws IOException
	 */
	public boolean ReplaceRegister(Register Reg,String key) throws FileNotFoundException, DeserializationException, IOException {
		JsonObject J = (JsonObject) Jsoner.deserialize(new FileReader("users.json"));
		JsonObject Aux = new JsonObject();
		Aux.put("Nome",Reg.getName());
		Aux.put("UIN",Reg.getUIN());
		Aux.put("LastName", Reg.getLastName());
		Aux.put("password", Reg.getPass());
		Aux.put("salt", Reg.getSalt());
		J.replace(key, Aux);
		FileWriter file = new FileWriter("users.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
		return true;
	}
	public boolean ReplaceFeed(Feed F,String key) throws FileNotFoundException, DeserializationException, IOException {
		JsonObject J = (JsonObject) Jsoner.deserialize(new FileReader("feed.json"));
		JsonObject NewObj = new JsonObject();
		NewObj.put("date", Integer.toString(F.getDate()));
		NewObj.put("ppage", Integer.toString(F.getPPage()));
		NewObj.put("posts", F.getPosts());
		NewObj.put("cont",F.getcont());
		J.replace(F.getFeedId(), NewObj);
		System.out.println(J.toJson());
		FileWriter file = new FileWriter("feed.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
		return true;
	}
	public boolean ReplaceComrade(Comrade Com,String key) throws FileNotFoundException, DeserializationException, IOException {
		JsonObject J = (JsonObject) Jsoner.deserialize(new FileReader("comrade.json"));
		JsonObject NewObj = new JsonObject();
		NewObj.put("Nome", Com.getName());
		NewObj.put("LastName", Com.getLastName());
		NewObj.put("comrade", Com.getComrades());
		NewObj.put("pending", Com.getPendingComrades());
		NewObj.put("pages", Com.getPages());
		NewObj.put("groups", Com.getGroups());
		NewObj.put("messages", Com.getMessages());
		NewObj.put("age", Com.getAge());
		NewObj.put("birthday",Com.getBirthday());
		NewObj.put("photoPath", Com.getPhotoPath());
		J.replace(Com.getUserName(), NewObj);
		System.out.println(J.toJson());
		FileWriter file = new FileWriter("comrade.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
		return true;
	}
	public boolean ReplaceGroup(Group G) throws FileNotFoundException, DeserializationException, IOException {
		JsonObject J = (JsonObject) Jsoner.deserialize(new FileReader("groups.json"));
		JsonObject NewObj = new JsonObject();
		NewObj.put("admin", G.getAdmin());
		NewObj.put("name", G.getName());
		NewObj.put("members", G.getMembers());
		J.replace(G.getGroupId(), NewObj);
		System.out.println(J.toJson());
		FileWriter file = new FileWriter("groups.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
		return true;
	}
	public boolean ReplacePage(Page P) throws FileNotFoundException, DeserializationException, IOException {
		JsonObject J = (JsonObject) Jsoner.deserialize(new FileReader("pages.json"));
		JsonObject NewObj = new JsonObject();
		NewObj.put("owner",P.getOwner());
		NewObj.put("name", P.getName());
		NewObj.put("followers", P.getFollowers());
		J.replace(P.getPageId(), NewObj);
		System.out.println(J.toJson());
		FileWriter file = new FileWriter("pages.json");
		BufferedWriter BW = new BufferedWriter(file);
		BW.write(J.toJson());
		BW.newLine();
		BW.flush();
		BW.close();
		return true;
	}
	
}
