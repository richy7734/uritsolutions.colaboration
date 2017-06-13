package com.niit.uritsolution.dao;

import java.util.List;

import com.niit.uritsolution.model.Comment;
import com.niit.uritsolution.model.Post;

public interface PostDao {
	
	public List<Post> getPost();
	public void addPost(Post post);
	public Post getPostByPostTitle(String postTitle);
	public List<Comment> getComment(int id);
	public void comment(Comment comment);
	public List<Post> getPostListByFrmId(int frmId);
	public Post getPostById(int id);

}
