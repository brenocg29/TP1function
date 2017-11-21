package siteEntities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;

import dao.JsonHandler;
import siteEntities.Comrade;
/**
 * Represents a page
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 *
 */
public class Page {
	private String name;
	private String Owner;
	private JsonArray followers;
	private String PageId;
	/**
	 * Constructs a new page
	 * @param name
	 * @param NumberFollowers
	 * @param owner
	 * @throws IOException 
	 * @throws DeserializationException 
	 */
	public Page(String name,String owner) throws IOException, DeserializationException{
		System.out.println("holllaa");
		this.name = name;
		this.PageId = name + "page";
		this.Owner = owner;
		this.followers = new JsonArray();
		JsonHandler FG = new JsonHandler("page");
		boolean test = FG.SaveJson(this);
		if (test == false) {
			System.out.println("escolha outro nome para a pagina");
			return;
		}
		Feed D = new Feed(1,1,this.name + "pfeed");
		JsonHandler FJ = new JsonHandler("feed");
		FJ.SaveJson(D);
	}
	/**
	 * Constructor for an existing page
	 * @param name
	 * @param owner
	 * @param followers
	 * @param ID
	 * @throws FileNotFoundException
	 * @throws DeserializationException
	 * @throws IOException
	 */
	public Page(String name,String owner, JsonArray followers,String ID) throws FileNotFoundException, DeserializationException, IOException{
		this.name = name;
		this.PageId = ID;
		this.Owner = owner;
		this.followers = followers;
	}
	/**
	 * Make a new post in the page
	 * @param P Post Object
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public boolean MakePost(Post P) throws FileNotFoundException, DeserializationException, IOException {
		if (P.getPoster().equals(Owner)) {
			JsonHandler J = new JsonHandler("feed");
			Feed F = J.readFFromJson(this.name + "pfeed");
			if(F==null) {
				System.out.println("algo deu errado");
			}
			F.AppendPost(P);
			return true;
		}
		else {
			System.out.println("você não é o dono desta pagina");
			return false;
		}
	}
	/**
	 * Expells a Comrade from the page
	 * @param C Comrade to be expelled
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void ExpelComrade(Comrade C) throws FileNotFoundException, DeserializationException, IOException{
		JsonHandler memberChanger = new JsonHandler("page");
		JsonHandler Cmember = new JsonHandler("comrade");
		C.getPages().remove(this.getName());
		this.followers.remove(C.getUserName());
		memberChanger.ReplacePage(this);
		Cmember.ReplaceComrade(C, C.getUserName());
	}
	/**
	 * Split the page in two, making social justice
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void Reform() throws FileNotFoundException, DeserializationException, IOException{
		if (this.followers.size() >= 3) {
			System.out.println("Reforming");
			JsonHandler J = new JsonHandler("page");
			JsonHandler F = new JsonHandler("feed");
			Feed F1 = new Feed(1,1,this.name+"1" + "pfeed");
			JsonHandler Member = new JsonHandler("comrade");
			Comrade C = null;
			JsonArray page2followers = new JsonArray();
			Page newP = new Page(this.name+"1",this.getFollowers().getString(1),page2followers,this.name+"1"+"page");
			J.SaveJson(newP);
			for (int i = 0; i< 2;i++) {
				C = Member.readCFromJson(this.getFollowers().getString(i));
				C.getPages().remove(this.getName());
				C.followPage(newP);
				String added = (String) this.followers.remove(i);
				Member.ReplaceComrade(C,C.getUserName());
			}
			F.SaveJson(F1);
			J.ReplacePage(this);
			J.ReplacePage(newP);
		}
	}
	public boolean addFollower(Comrade Com) throws FileNotFoundException, DeserializationException, IOException {
		
		JsonHandler J = new JsonHandler("page");
		if (this.followers.contains(Com.getUserName())) {
			return false;
		}
		this.followers.add(Com.getUserName());
		J.ReplacePage(this);
		
		return false;
		
	}
	public String getName() {
		return name;
	}
	public String getOwner() {
		return Owner;
	}
	public JsonArray getFollowers() {
		return followers;
	}
	public String getPageId() {
		return PageId;
	}
	
}
