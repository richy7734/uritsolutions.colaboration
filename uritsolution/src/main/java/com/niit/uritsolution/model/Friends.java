package com.niit.uritsolution.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friends {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private int userId;
	private int frndId;
	private String name;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFrndId() {
		return frndId;
	}

	public void setFrndId(int frndId) {
		this.frndId = frndId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
