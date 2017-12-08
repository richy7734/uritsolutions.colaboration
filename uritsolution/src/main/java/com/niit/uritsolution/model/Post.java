package com.niit.uritsolution.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Component;

@Entity
@Component("post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String title;

	private String category;

	@Column(columnDefinition = "LONGVARCHAR")
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POST_DATE", columnDefinition = "TIMESTAMP")
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	private int grpId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Comment> Comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Comment> getComments() {
		return Comments;
	}

	public void setComments(List<Comment> comments) {
		Comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", category=" + category + ", content=" + content + ", date="
				+ date + ", user=" + user + ", grpId=" + grpId + ", Comments=" + Comments + "]";
	}

}
