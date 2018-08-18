package com.plivo.msgapi.model;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class Message {

	private long msgId = 0;
	private String msgBody = null;
	
	
	public Message() {
		super();
	}
	public Message(long msgId, String msgBody) {
		super();
		this.msgId = msgId;
		this.msgBody = msgBody;
	}
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	
	
}
