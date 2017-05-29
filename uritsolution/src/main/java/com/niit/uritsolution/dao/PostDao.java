package com.niit.uritsolution.dao;

import java.util.List;

import com.niit.uritsolution.model.Post;

public interface PostDao {
	
	public List<Post> getPost();
	public void addPost(Post post);
	public Post getPostByPostTitle(String postTitle);

}
