package back;
import back.JsonHandler;
import back.Comrade;
/**
 * Represent a post
 * @author Breno Chaves Gabrich
 * @author Gabriel Pires
 * @author Fernanda Ramalho
 */
public class Post {
	private Comrade Poster;
	private String data;
	private int LikeNum;
	private String date;
	private String[] comments;
	/**
	 * Construct a Post
	 * @param poster Person making the post
	 * @param data Text of the post
	 * @param LikeNum Numbers of likes in the post
	 * @param date Date of the post
	 */
	Post(Comrade poster, String data, int LikeNum,String date ){
		this.Poster = poster;
		this.data = data;
		this.LikeNum = LikeNum;
		this.date = date;
	}
	public Comrade getPoster() {
		return Poster;
	}
	public String getData() {
		return data;
	}
	public String getDate() {
		return date;
	}
	public String[] getComments() {
		return comments;
	}
	public int getLikeNum() {
		return LikeNum;
	}
	public void IncreaseLikeNum() {
		LikeNum+=1;
	}
	public void makecomment(Comrade Commenter, String comment) {
		
	}
}
