package com.niit.uritsolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.uritsolution.dao.PostDao;
import com.niit.uritsolution.model.Comment;
import com.niit.uritsolution.model.Post;
import com.niit.uritsolution.model.Responce;

@RestController
public class PostController {

	@Autowired
	PostDao postDao;

	@RequestMapping("/save/post")
	public ResponseEntity<Post> savePost(@RequestBody Post post) {
		System.out.println("------------------Save Post Handler reached.------------------");
		postDao.addPost(post);

		Responce responce = new Responce();
		responce.setResponceCode(1);
		responce.setReposneMessge("Post Saved..!!");
		
		System.out.println(responce.getReposneMessge());
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}

	@RequestMapping("/get/post")
	public List<Post> getPost() {
		return postDao.getPost();
	}
	
	@RequestMapping("/get/post/{postTitle}")
	public Post getPostByTitle(@PathVariable("postTitle")String postTitle){
		return postDao.getPostByPostTitle(postTitle);
	}
	
	@RequestMapping("/get/comments/{id}")
	public List<Comment> getAllComments(@PathVariable("id")int id){
		return postDao.getComment(id);
	}
	
	@RequestMapping("/comment/save/{pid}/{username}")
	public List<Comment> commentSave(@RequestBody String content,@PathVariable("pid") int pid,@PathVariable("username") String username){
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setPid(pid);
		comment.setUsername(username);
		System.out.println("Comment is :"+comment.getContent());
		postDao.comment(comment);
		return postDao.getComment(comment.getPid());
	}
}
