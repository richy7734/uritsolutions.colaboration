package com.niit.uritsolution.model;

import java.util.Date;

public class OutMessage extends Message {

	private Date time;

	public OutMessage(Message original, Date time) {
		super(original.getId(), original.getMsg());
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
