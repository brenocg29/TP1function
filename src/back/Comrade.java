package back;
import back.JsonHandler;
import back.Group;
import back.Page;
import back.Post;

/** represents an user of the social network.
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 */
public class Comrade {
	private String name;
	private String age;
	private String id;
	private String birthday;
	private String PhotoPath;
	
//	Ideias:
//	private String unit; //Unidade militar
	
	/** represents user data
	 * 
	 * @param name The user name
	 * @param age The user age
	 * @param Id The user ID on the network
	 * @param birthday The user birthDay
	 * @param PhotoPath The location of user photo
	 */
	Comrade(String name, String age, String Id, String birthday,String PhotoPath) {
		
	}
	
	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public String getId() {
		return id;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getPhotoPath() {
		return PhotoPath;
	}
	/**
	 *  Allow user to change its photo in the network
	 * @param newPath Location of new Photo
	 */
	public void changePhoto(String newPath) {
		
	}
	/**
	 * Allow user to enter a group
	 * @param G object representig the group
	 * @return Result of operation
	 */
	public boolean joinGroup(Group G) {
		return true;
	}
	/**
	 * Show other users associated with this user
	 * @return result of operation
	 */
	public boolean ShowComrades() {
		return true;
	}
	/**
	 * Allows user to connect with other user
	 * @param NewCom object representing user
	 */
	public void addComrade(Comrade NewCom){
		
	}
	/**
	 * Allow user to leave a group
	 * @param G Group object
	 */
	public void LeaveGroup(Group G) {
		
	}
	/**
	 * Allow user to follow a page
	 * @param P Page Object
	 */
	public void followPage(Page P) {
		
	}
	/**
	 * Allow user to post something
	 */
	public void MakePost() {
		
	}
	/**
	 * Allow user to send messages to other users
	 * @param receiver Object representing the receiver
	 * @param text Text of message
	 */
	public void sendMessage(Comrade receiver, String text) {
		
	}
}
