package com.plivo.msgapi.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class Message {

	private long msgId = 0;
	private String msgBody = null;
	private Timestamp msgTimeStamp = null;
	
	
	public Timestamp getMsgTimeStamp() {
		return msgTimeStamp;
	}
	public void setMsgTimeStamp(Timestamp msgTimeStamp) {
		this.msgTimeStamp = msgTimeStamp;
	}
	public Message() {
		super();
	}
	public Message(long msgId, String msgBody,Timestamp ts) {
		super();
		this.msgId = msgId;
		this.msgBody = msgBody;
		this.msgTimeStamp = ts;
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
