package academy.prog;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*

	C-> 			S						List
				/add POST(JSON message) ->  0
				/get?from=3 GET				1
											2'
											3
 */

public class Message {
	private Date date = new Date();
	private String from;
	private String to;
	private String text;
	private String room;

	public Message(String from, String text) {
		this.from = from;
		this.text = text;
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}
	
	public static Message fromJSON(String s) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(s, Message.class);
	}
	
	@Override
	public String toString() {
		String res = "";
		if (to == null && room == null) {
			res = new StringBuilder().append("public -> [").append(date)
					.append(", From: ").append(from)
					.append("] ").append(text)
					.toString();
		}else if (room == null){
			res = new StringBuilder().append("private -> [").append(date)
					.append(", From: ").append(from).append(", To: ").append(to)
					.append("] ").append(text)
					.toString();
		}else{
			res = new StringBuilder().append("ChatRoom -> [").append(date)
					.append(", From: ").append(from).append(", Room: ").append(room)
					.append("] ").append(text)
					.toString();
		}

		return res;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRoom() { return room; }

	public void setRoom(String room) { this.room = room; }
}
