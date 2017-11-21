package siteEntities;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;

import dao.JsonHandler;
import siteEntities.Comrade;
import siteEntities.Post;
/**
 * 
 * @author Breno Chaves Gabrich
 * @author Gabriel Pires
 * @author Fernanda Ramalho
 *
 */
public class Feed {
	private int date;
	private int cont;
	private int PPage;
	private String FeedId;
	private JsonArray posts;
	/**
	 * Constructor to create a feed
	 * @param Date date of feed
	 * @param PPage number of posts per page
	 */
	public Feed(int Date, int PPage,String feedid){
		this.FeedId = feedid;
		this.date = Date;
		this.PPage = PPage;
		this.posts = new JsonArray(); 
		this.cont = 0;
	}
	/**
	 * Constructor to load a feed
	 * @param Date
	 * @param PPage
	 * @param feedid
	 * @param posts
	 */
	public Feed(int Date, int PPage,String feedid,JsonArray posts,int cont){
		this.posts = posts;
		this.FeedId = feedid;
		this.date = Date;
		this.PPage = PPage;
		this.posts = posts;
		this.cont = cont;
	}
	/**
	 * Change the number of posts to be loaded
	 * @param newNum new number of posts
	 */
	public void ChangePPage(int newNum) {
	this.PPage = newNum;	
	}
	public void removePost(int index) {
		posts.remove(index);
	}
	public void deletePost(int index) throws FileNotFoundException, DeserializationException, IOException {
		for (int i = 0; i < posts.size();i++) {
			JsonObject post = (JsonObject) posts.get(i);
			System.out.println("wat " + post.get("cont") + " " + index);
			if(post.getInteger("cont") == index){
				JsonHandler saver = new JsonHandler("feed");
				posts.remove(i);
				System.out.println("entrei");
				saver.ReplaceFeed(this, this.getFeedId());
				break;
			}
		}
	}
	public void AppendPost(Post P) throws FileNotFoundException, DeserializationException, IOException {
		this.setcont(this.getcont()+1);
		JsonHandler J = new JsonHandler("feed");
		JsonObject Post = new JsonObject();
		Post.put("poster", P.getPoster());
		Post.put("comment",new JsonArray());
		Post.put("data", P.getDate());
		Post.put("msg", P.getData());
		Post.put("cont", this.getcont());
		Post.put("likes", new JsonArray());
		Post.put("feedid", this.getFeedId());
		this.posts.add(Post);
		J.ReplaceFeed(this,this.FeedId);
	}
	public int getDate() {
		return date;
	}
	public int getPPage() {
		return PPage;
	}
	public String getFeedId() {
		return FeedId;
	}
	public int getcont() {
		return cont;
	}
	public void setcont(int c) {
		cont = c;
	}
	public JsonArray getPosts() {
		return posts;
	}
}
