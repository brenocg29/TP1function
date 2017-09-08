package back;
import back.JsonHandler;
import back.Comrade;
//import post when ready
public class Group {
	private String name;
	private String GroupId;
	private String[] members;
	//Post[] posts
	Group(String name, String GroupId,String[] members){
		
	}
	public String GetName() {
		return "test";
	}
	public void AddMember() {
		
	}
	public void ChangeName() {
		
	}
	//public Comrade[] showMembers() {
	public String getName() {
		return name;
	}
	public String getGroupId() {
		return GroupId;
	}
	public String[] getMembers() {
		return members;
	}
		
	//}
}
