package com.niit.uritsolution.model;

public class Responce {

	private String reposneMessge;
	private int responceCode;

	public String getReposneMessge() {
		return reposneMessge;
	}

	public void setReposneMessge(String reposneMessge) {
		this.reposneMessge = reposneMessge;
	}

	public int getResponceCode() {
		return responceCode;
	}

	public void setResponceCode(int responceCode) {
		this.responceCode = responceCode;
	}

	public Responce(String reposneMessge, int responceCode) {
		super();
		this.reposneMessge = reposneMessge;
		this.responceCode = responceCode;
	}

	public Responce() {
	}
}
