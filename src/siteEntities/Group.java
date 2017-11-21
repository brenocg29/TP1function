package siteEntities;
import siteEntities.Comrade;
import siteEntities.Post;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;

import dao.JsonHandler;
/**
 * Represents a Group in the network
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 *
 */
public class Group {
	private String name;
	private String GroupId;
	private JsonArray members; //Aqui seria os IDs, certo?
	private String admin;
//	private Comrade[] admins; //Admins / moderadores (Commissars em russo soviético)
	
	/**
	 * Constructor for a new group
	 * @param name name of the group 
	 * @param GroupId id of the group
	 * @param members members of this group
	 * @throws IOException 
	 * @throws DeserializationException 
	 */
	public Group(String admin,String name) throws IOException, DeserializationException{
		this.admin = admin;
		this.name = name;
		this.GroupId = name + "group";
		this.members = new JsonArray();
		JsonHandler FG = new JsonHandler("group");
		boolean test = FG.SaveJson(this);
		if (test == false) {
			System.out.println("Escolha outro nome para o grupo");
			return;
		}
		Feed D = new Feed(1,1,this.name + "gfeed");
		JsonHandler FJ = new JsonHandler("feed");
		FJ.SaveJson(D);
	}
	/**
	 * Constructor for an existing group
	 * @param admin
	 * @param name
	 * @param GroupId
	 * @param JA
	 * @throws IOException
	 * @throws DeserializationException
	 */
	public Group(String admin,String name, String GroupId,JsonArray JA) throws IOException, DeserializationException{
		this.admin = admin;
		this.name = name;
		this.GroupId = name + "group";
		this.members = JA;
	}
	/**
	 * Post in this group
	 * @param P your post
	 * @return
	 * @throws FileNotFoundException
	 * @throws DeserializationException
	 * @throws IOException
	 */
	public boolean PostOnGroup(Post P) throws FileNotFoundException, DeserializationException, IOException {
		if (this.members.contains(P.getPoster())) {
		JsonHandler J = new JsonHandler("feed");
		Feed F = J.readFFromJson(this.getName() + "gfeed");
		if(F==null) {
			System.out.println("Algo deu errado");
			return false;
		}
		F.AppendPost(P);	
		return true;
	}
		System.out.println("voce nao esta neste grupo");
		return false;
		}
	public String getAdmin() {
		return admin;
	}
	/**
	 * Add a memeber to this group
	 * @param com new member object 
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void AddMember(Comrade com) throws FileNotFoundException, DeserializationException, IOException {
		JsonHandler J = new JsonHandler("group");
		if (this.members.contains(com.getUserName())) {
			return;
		}
		this.members.add(com.getUserName());
		J.ReplaceGroup(this);
		
	}
	/**
	 * Change name of this group
	 * @param NewName new name of the group
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void ChangeName(String NewName) throws FileNotFoundException, DeserializationException, IOException {
		JsonHandler NameChanger = new JsonHandler("group");
		this.name = NewName;
		NameChanger.ReplaceGroup(this);
	}
	public String getName() {
		return name;
	}
	public String getGroupId() {
		return GroupId;
	}
	public JsonArray getMembers() {
		return members;
	}
	/**
	 * Add a new member to the group
	 * @param newMember New member object
	 */
	/**
	 * Expel a member of the group
	 * @param Member member object to be expelled
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void expelMember(Comrade Member) throws FileNotFoundException, DeserializationException, IOException {
		
		JsonHandler memberChanger = new JsonHandler("group");
		this.members.remove(Member.getUserName());
		memberChanger.ReplaceGroup(this);
		JsonHandler comChanger = new JsonHandler("comrade");
		Member.getGroups().remove(this.getName());
		comChanger.ReplaceComrade(Member, Member.getUserName());
	}
}
