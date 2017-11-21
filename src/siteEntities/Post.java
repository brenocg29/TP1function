package siteEntities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import dao.JsonHandler;
import siteEntities.Comrade;
import siteEntities.Feed;
/**
 * Represent a post
 * @author Breno Chaves Gabrich
 * @author Fernanda Ramalho
 * @author Gabriel Pires
 */
public class Post {
	private String feedID;
	private int postID;
	private String Poster;
	private String data;
	private int LikeNum; //Ура!(Ura!) https://en.wikipedia.org/wiki/File:%D0%A3%D1%80%D0%B0!.ogv
	private String date;
	private JsonArray comments;
	/**
	 * Construct a Post
	 * @param poster Person making the post
	 * @param data Text of the post
	 * @param LikeNum Numbers of likes in the post
	 * @param date Date of the post
	 */
	public Post(String poster, String data, int LikeNum,String feedId){
		this.feedID = feedId;
		this.Poster = poster;
		this.data = data;
		this.LikeNum = LikeNum;
		DateFormat now = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date today = new Date();
		this.date = now.format(today);
		this.comments = new JsonArray();
	}
	public int getpostdID() {
		return postID;
	}
	public String getFeedID() {
		return feedID;
	}
	public String getPoster() {
		return Poster;
	}
	public String getData() {
		return data;
	}
	public String getDate() {
		return date;
	}
	public JsonArray getComments() {
		return comments;
	}
	public int getLikeNum() {
		return LikeNum;
	}
	public void IncreaseLikeNum() {
		LikeNum+=1;
	}
	public static void makecomment(Comrade Commenter, String data,int index,String feedId) throws DeserializationException, FileNotFoundException, IOException {
		JsonHandler H = new JsonHandler("feed");
		JsonObject J = new JsonObject();
		J.put("posterId", Commenter.getUserName());
		J.put("msg", data);
		Feed F = new JsonHandler("feed").readFFromJson(feedId);
		JsonArray posts = F.getPosts();
		for (int i = 0;i<F.getPosts().size();i++) {
			JsonObject post = (JsonObject) posts.get(i);
			if(post.getInteger("cont") == index){
				JsonObject Post = (JsonObject) F.getPosts().get(i);
				JsonArray JA = (JsonArray) Post.get("comment");
				JA.add(J);
				Post.put("comment", JA);
				F.getPosts().remove(i);
				F.getPosts().add(Post);
				H.ReplaceFeed(F,F.getFeedId());
			}


		}
	}
	public static void appendlike(Comrade liker,String feedId, int index) throws FileNotFoundException, DeserializationException, IOException {
		JsonHandler H = new JsonHandler("feed");
		Feed F = new JsonHandler("feed").readFFromJson(feedId);
		JsonArray posts = F.getPosts();
		for (int i = 0;i<F.getPosts().size();i++) {
			JsonObject post = (JsonObject) posts.get(i);
			if(post.getInteger("cont") == index){
				JsonArray JA = (JsonArray) post.get("likes");
				if(JA.contains(liker.getUserName())) {
					JA.remove(liker.getUserName());
					post.put("likes", JA);
					F.getPosts().remove(i);
					F.getPosts().add(post);
					H.ReplaceFeed(F,F.getFeedId());
					return;
				}
				JA.add(liker.getUserName());
				post.put("likes", JA);
				F.getPosts().remove(i);
				F.getPosts().add(post);
				H.ReplaceFeed(F,F.getFeedId());
			}
	}
		}
}
