package back;
import back.Comrade;
import back.JsonHandler;
/**
 * Represents a page
 * @author Breno Chaves Gabrich
 * @author Gabriel Pires
 * @author Fernanda Ramalho
 *
 */
public class Page {
	private String name;
	private String numberFollowers;
	private Comrade Owner;
	/**
	 * Constructs a page
	 * @param name
	 * @param NumberFollowers
	 * @param owner
	 */
	Page(String name, String NumberFollowers,Comrade owner){
		this.name = name;
		this.numberFollowers = NumberFollowers;
		this.Owner = owner;
	}
	public void MakePost() {
		
	}
	public void ExpelComrade(){
		
	}
	public void Reform(){
		
	}
	public void CreatePage(//page data
			) {
		
	}
}
