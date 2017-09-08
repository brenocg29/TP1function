package back;
import back.JsonHandler;
import back.Comrade;
public class Post {
	private Comrade Poster;
	private String data;
	private int LikeNum;
	private String date;
	private String[] comments;
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
	public void setLikeNum(int likeNum) {
		LikeNum = likeNum;
	}
	public void makecomment(Comrade Commenter, String comment) {
		
	}
}
