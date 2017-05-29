package com.niit.uritsolution.model;

public class Message {

	private String msg;
	private int id;

	public Message() {

	}

	public Message(int id, String message) {
		this.id = id;
		this.msg = message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String message) {
		this.msg = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
