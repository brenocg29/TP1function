package back;
import back.Comrade;
import back.JsonHandler;
import back.Post;
/**
 * 
 * @author Breno Chaves Gabrich
 * @author Gabriel Pires
 * @author Fernanda Ramalho
 *
 */
public class Feed {
	private int date;
	private int PPage;
	private Post[] posts;
	/**
	 * Construct a new feed loading posts for current time
	 * @param Date date of feed
	 * @param PPage number of posts per page
	 */
	Feed(int Date, int PPage){
		this.date = Date;
		this.PPage = PPage;
	}
	/**
	 * Change the number of posts to be loaded
	 * @param newNum new number of posts
	 */
	public void ChangePPage(int newNum) {
		
	}
	/**
	 * Load more posts (number os posts per page
	 */
	public void LoadMorePost() {
		
	}
	/**
	 * Show loaded posts
	 */
	public void ShowPosts() {
		
	}
}
