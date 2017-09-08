package back;
import org.json.*;
import back.*;
/**
 * Class to handle all writing to json
 * @author Breno Chaves Gabrich
 * @author Gabriel Pires
 * @author Fernanda Ramalho
 */
public class JsonHandler {
	private String pathToFile;
	/**
	 * 
	 * @param X Write an object to json, the object can be an comrade a page a post or a group
	 * @return result of operation
	 */
	public boolean SaveJson(Comrade Com){
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
	 * @param X Read from json, the object can be a comrade page, group or post
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
