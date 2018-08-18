package com.plivo.msgapi.model;




public class User {

	private long userId = 0;
	private String userName = null;
	
	
	public User() {
		super();
	}
	public User(long userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setMsgBody(String msgBody) {
		this.userName = msgBody;
	}
	
	
}
