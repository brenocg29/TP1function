package siteEntities;
import siteEntities.Group;
import siteEntities.Page;
import siteEntities.Post;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;

import dao.JsonHandler;

/** represents an user of the social network.
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 */
public class Comrade {
	private String name;
	private String lastName;
	private String age;
	private String UserName;
	private String birthday;
	private String PhotoPath;
	private JsonArray Groups;
	private JsonArray Comrades;
	private JsonArray PendingComrades;
	private JsonArray Pages;
	private JsonArray Messages;
	private int numberOfFolowers;
	private boolean isLeader;
	private int praiseCount;
	
	/** represents user data
	 * 
	 * @param name The user name
	 * @param age The user age
	 * @param Id The user ID on the network
	 * @param birthday The user birthDay
	 * @param PhotoPath The location of user photo
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	
	public Comrade(String name,String LastName,String username) throws FileNotFoundException, DeserializationException, IOException {
		this.name = name;
		this.lastName = LastName;
		this.age = " ";
		this.birthday = " ";
		this.PhotoPath = " ";
		this.UserName = username;
		this.Comrades = new JsonArray();
		this.PendingComrades = new JsonArray();
		this.Groups = new JsonArray();
		this.Pages = new JsonArray();
		this.Messages = new JsonArray();
		this.numberOfFolowers = 0;
		this.isLeader = false;
		this.praiseCount = 0;
		Feed D = new Feed(1,1,this.UserName + "cfeed");
		JsonHandler FJ = new JsonHandler("feed");
		FJ.SaveJson(D);

	}
	public Comrade(String name,String LastName,String username,JsonArray Comrades,JsonArray Pending,JsonArray Pages,JsonArray Groups,JsonArray Messages,String birthday, String photopath,String Age) {
		this.name = name;
		this.lastName = LastName;
		this.age = Age;
		this.birthday = birthday;
		this.PhotoPath = photopath;
		this.UserName = username;
		this.Comrades = Comrades;
		this.PendingComrades = Pending;
		this.Groups = Groups;
		this.Pages = Pages;
		this.Messages = Messages;
	}
	
	//=============Regras de Negócio====================
	/* Methods */
	/**
	 * Allow user to become a leader
	 * @return result of operation
	 */
	public boolean becomeLeader() {
		if(this.numberOfFolowers >= 100) {
			this.isLeader = true;
			return true;
		}else {
			return false;
		}
	}
	
	public JsonArray getMessages() {
		System.out.println("this is the folder" + System.getProperty("user.dir")) ;
		return this.Messages;
	}
	/**
	 * Allow user to praise a leader, increasing it's praise count
	 */
	public void praiseLeader(Comrade praiseTarget) {
		if(praiseTarget.isLeader /*&& !praiseOnCooldown*/) {
			praiseTarget.praiseCount++;
		}
	}
	public void UndoComrade(Comrade C) throws FileNotFoundException, DeserializationException, IOException {
		this.getComrades().remove(C.UserName);
		C.getComrades().remove(this.getUserName());
		JsonHandler writer = new JsonHandler("comrade");
		writer.ReplaceComrade(C, C.getUserName());
		writer.ReplaceComrade(this, this.getUserName());
		
	}
	/* Getters and Setters */
	public int getNumberOfFolowers() {
		return numberOfFolowers;
	}
	public boolean getLeaderStatus() {
		return isLeader;
	}
	public int getPraiseCount() {
		return praiseCount;
	}
	public String getName() {
		return name;
	}

	public JsonArray getGroups() {
		return Groups;
	}
	public JsonArray getComrades() {
		return Comrades;
	}
	public JsonArray getPages() {
		return Pages;
	}
	public String getAge() {
		return age;
	}
	public void createGroup(String name) throws IOException, DeserializationException {
		Group G = new Group(this.getUserName(),name);
		this.joinGroup(G);
	}
	public void createPage(String name) throws IOException, DeserializationException {
		Page P = new Page(name,this.getUserName());
		this.followPage(P);
	}
	public boolean SaveComrade() throws JSONException, IOException, DeserializationException {
		JsonHandler J = new JsonHandler("comrade");
		J.SaveJson(this);
		return true;
	}
	public String getBirthday() {
		return birthday;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setPhotoPath(String photoPath) {
		PhotoPath = photoPath;
	}

	public String getPhotoPath() {
		return PhotoPath;
	}
	
	public String getLastName() {
		return lastName;
	}
	public String getUserName() {
		return UserName;
	}
	/**
	 * Allow user to enter a group
	 * @param G object representig the group
	 * @return Result of operation
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public boolean joinGroup(Group G) throws FileNotFoundException, DeserializationException, IOException {
		JsonHandler J = new JsonHandler("comrade");
		JsonHandler auxCheck = new JsonHandler("group");
		if(auxCheck.readGFromJson(G.getGroupId())!=null) 
		{
			G.AddMember(this);	
			this.getGroups().add(G.getName());
			J.ReplaceComrade(this, this.UserName);
			return true;
		}
		return false;
	}
	/**
	 * Show other users associated with this user
	 * @return result of operation
	 */
	public JsonArray ShowComrades() {
		return Comrades;
		
	}
	/**
	 * Allows user to connect with other user
	 * @param NewCom object representing user
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void addComrade(Comrade NewCom) throws FileNotFoundException, DeserializationException, IOException{
		JsonHandler J = new JsonHandler("comrade");
		JsonHandler auxCheck = new JsonHandler("comrade");
		if(auxCheck.readCFromJson(NewCom.getUserName())!=null) 
		{
			Comrade Com = auxCheck.readCFromJson(this.UserName);
			Com.getComrades().add(NewCom.getUserName());
			J.ReplaceComrade(Com, this.UserName);
			//----------------------------------------
			Comrade userObj2 =  J.readCFromJson(NewCom.UserName);
			if (userObj2.getPendingComrades().contains(this.UserName)) {
				return;
			}
			userObj2.getPendingComrades().add(this.UserName);
			J.ReplaceComrade(userObj2, NewCom.getUserName());
		}
	}
	/**
	 * Accept a pending user
	 * @param PendingUser username 
	 * @throws FileNotFoundException
	 * @throws DeserializationException
	 * @throws IOException
	 */
	public void AcceptPending(String PendingUser) throws FileNotFoundException, DeserializationException, IOException {
		JsonHandler J = new JsonHandler("comrade");
		Comrade userObj =  J.readCFromJson(this.UserName);
		if (userObj.getComrades().contains(PendingUser)) {
			System.out.println("you are already comrades");
			return;
		}
		if (userObj.getPendingComrades().contains(PendingUser)) {
			userObj.getPendingComrades().remove(PendingUser);
			userObj.getComrades().add(PendingUser);
			J.ReplaceComrade(userObj, this.UserName);
			return;
		}
		else {
			System.out.println("No such user");
		}
	}
	/**
	 * Allow user to leave a group
	 * @param G Group object
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void LeaveGroup(Group G) throws FileNotFoundException, DeserializationException, IOException {
		G.expelMember(this);
		
	}
	/**
	 * Allow user to follow a page
	 * @param P Page Object
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void followPage(Page P) throws FileNotFoundException, DeserializationException, IOException {
		JsonHandler J = new JsonHandler("comrade");
		JsonHandler auxCheck = new JsonHandler("page");
		if(auxCheck.readPFromJson(P.getPageId())!=null) 
		{
			P.addFollower(this);
			System.out.println("ola");
			this.getPages().add(P.getName());
			J.ReplaceComrade(this, this.UserName);
		}
	}
	/**
	 * Allow user to post something
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void MakePost(Post P) throws FileNotFoundException, DeserializationException, IOException {
		JsonHandler J = new JsonHandler("feed");
		Feed F = J.readFFromJson(this.UserName + "cfeed");
		if (F == null) {
			System.out.println("Algo deu errado");
			return;
		}
		F.AppendPost(P);
		J.ReplaceFeed(F, this.UserName + "cfeed");
	}
	/**
	 * Allow user to send messages to other users
	 * @param receiver Object representing the receiver
	 * @param text Text of message
	 * @throws IOException 
	 * @throws DeserializationException 
	 * @throws FileNotFoundException 
	 */
	public void sendMessage(Comrade receiver, String text,String title) throws FileNotFoundException, DeserializationException, IOException {
		if (this.getComrades().contains(receiver.getUserName()) && receiver.getComrades().contains(this.UserName)) {
			Message M = new Message(this.getName(),text,this.getUserName(),title);
			M.appendToReceiver(receiver);
		}
		else {
			System.out.println("Voce so pode enviar mensagens para seus camaradas");
		}
	}
	public JsonArray getPendingComrades() {
		return PendingComrades;
	}
}
