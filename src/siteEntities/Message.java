package siteEntities;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.DeserializationException;
import org.json.simple.JsonObject;

import dao.JsonHandler;
/**
 * Represents a message in the network
 * @author Breno Chaves Garbrich
 * @author Gabriel Pires
 * @author Fernanda Ramalho
 *
 */
public class Message {
	private String sender;
	private String msg;
	private String title;
	private String senderId;
	/**
	 * Constructor for a message
	 * @param sender
	 * @param msg
	 * @param senderId
	 */
	public Message(String sender, String msg, String senderId,String title) {
		this.sender = sender;
		this.msg = msg;
		this.senderId = senderId;
		this.title = title;
	}
	/**
	 * Append message to its receiver
	 * @param receiver username
	 * @throws FileNotFoundException
	 * @throws DeserializationException
	 * @throws IOException
	 */
	public void appendToReceiver(Comrade receiver) throws FileNotFoundException, DeserializationException, IOException {
		JsonHandler J = new JsonHandler("comrade");
		JsonObject Msg = new JsonObject();
		Msg.put("sender", this.sender);
		Msg.put("title", this.title);
		Msg.put("msg", this.msg);
		Msg.put("senderId", this.senderId);
		receiver.getMessages().add(Msg);
		J.ReplaceComrade(receiver, receiver.getUserName());
	}
}
