package com.niit.uritsolution.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Images {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	
	/*
	 * Getters and Setters.
	 * */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}