package com.plivo.msgapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageService {
	
	
	private Map<Long, Message> messagesMap = null;
	
	public MessageService() {
		// TODO Auto-generated constructor stub
		messagesMap = new HashMap<>();
		messagesMap.put(1L, new Message(1, "msgBody1"));
		messagesMap.put(2L, new Message(2, "msgBody2"));

	}
			
	public List<Message> addDummyMessages(){
		
		return new ArrayList<Message>(messagesMap.values());
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messagesMap.values());
	}
	
	public Message addMessage(Message message) {
		message.setMsgId(messagesMap.size() + 1);
		message.setMsgBody(message.getMsgBody()+message.getMsgId());
		messagesMap.put(message.getMsgId(), message);
		return message;
	}
	
	public Message getMessage(long id) {
		return messagesMap.get(id);
	}
	
	public Message updateMessage(Message message) {
		if (message.getMsgId() <= 0) {
			return null;
		}
		if(messagesMap.containsKey(message.getMsgId())) {
			messagesMap.put(message.getMsgId(), message);
			return message;
		}else {
			return null;
		}
	}
	
	
	public Message updateMessage(long id, Message message) {
		if (id <= 0) {
			return null;
		}
		if(messagesMap.containsKey(id)) {
			message.setMsgId(id);
			messagesMap.put(id, message);
			return message;
		}else {
			return null;
		}
	}

	
	public Message removeMessage(long id) {
		return messagesMap.remove(id);
	}
	

}
