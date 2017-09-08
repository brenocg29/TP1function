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
	/**
	 * Make a new post in the page
	 * @param P Post Objetc
	 */
	public void MakePost(Post P) {
		
	}
	/**
	 * Expells a Comrade from the page
	 * @param C Comrade to be expelled
	 */
	public void ExpelComrade(Comrade C){
		
	}
	/**
	 * Split the page in two, making social justice
	 */
	public void Reform(){
		
	}
	
}
