package back;
import back.*;
/**
 * Represent a profile for a user
 * @author Breno Chaves Gabrich
 * @author Gabriel Pires
 * @author Fernanda Ramalho
 */
public class Profile {
	private Comrade User;
	private boolean praise;
	private Group[] groups;
	private Page[] pages;
	private Comrade[] comrads;
	private Post[] posts;
	/**
	 * Create a Profile
	 * @param User person
	 * @param praise Check if the user praised its leader
	 * @param G Groups that user participates
	 * @param Pages pages that the user follows
	 * @param C comrades relates to person
	 * @param Posts posts that person did
	 */
	Profile(Comrade User, boolean praise, Group[] G, Page[] Pages, Comrade[] C, Post[] Posts){
		this.User = User;
		this.praise = praise;
		this.groups = G;
		this.pages = Pages;
		this.comrads = C;
		this.posts = Posts;
	}
	/**
	 * Show person groups
	 */
	public void ShowGroups(){
		
	}
	public Comrade getUser() {
		return User;
	}
	public boolean isPraise() {
		return praise;
	}
	public Group[] getGroups() {
		return groups;
	}
	public Page[] getPages() {
		return pages;
	}
	public Comrade[] getComrads() {
		return comrads;
	}
	public Post[] getPosts() {
		return posts;
	}
	/**
	 * Show user pages
	 */
	public void ShowPages() {
		
	}
	/**
	 * Show Comrades related to person
	 */
	public void ShowComrade() {
		
	}
}
