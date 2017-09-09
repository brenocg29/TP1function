package back;
import back.JsonHandler;
import back.Comrade;
import back.Post;
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
	private String[] members; //Aqui seria os IDs, certo?
	private Post[] posts;
//	private Comrade[] admins; //Admins / moderadores (Commissars em russo soviético)
	
	/**
	 * Constructor for an existing group
	 * @param name name of the group 
	 * @param GroupId id of the group
	 * @param members members of this group
	 */
	Group(String name, String GroupId,String[] members){
		
	}
	/**
	 * Add a memeber to this group
	 * @param com new member object 
	 */
	public void AddMember(Comrade com) {
		
	}
	/**
	 * Change name of this group
	 * @param NewName new name of the group
	 */
	public void ChangeName(String NewName) {
		
	}
	
//	public Comrade[] getAdmins() {
//		return admins;
//	}	
	public Post[] getPosts() {
		return posts;
	}
	public String getName() {
		return name;
	}
	public String getGroupId() {
		return GroupId;
	}
	public String[] getMembers() {
		return members;
	}
	/**
	 * Add a new member to the group
	 * @param newMember New member object
	 */
	public void addMember(Comrade newMember) {
		
	}
	/**
	 * Expel a member of the group
	 * @param Member member object to be expelled
	 */
	public void expelMember(Comrade Member) {
		
	}
	/**
	 * Create a new Group.
	 * @param Creator
	 */
	public static void NewGroup(Comrade Creator) {
		
	}
}
