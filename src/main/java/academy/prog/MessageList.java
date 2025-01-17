package academy.prog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
	private static final MessageList msgList = new MessageList();
    private final Gson gson;
	private final List<Message> list = new LinkedList<>();
	
	public static MessageList getInstance() {
		return msgList;
	}
  
  	private MessageList() {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}
	
	public synchronized void add(Message m) {
		list.add(m);
	}
	
	public synchronized String toJSON(int n, String userName) {
		if (n < 0 || n >= list.size()) return null;
		List<Message> filteredMessages = list.stream()
				.filter(a -> a.getTo() == null || a.getTo().equals(userName) || a.getFrom().equals(userName))
				.collect(Collectors.toList());
		System.out.println(gson.toJson(filteredMessages));
		return gson.toJson(new JsonMessages(filteredMessages, n));
	}
}
