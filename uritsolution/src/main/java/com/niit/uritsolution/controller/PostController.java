package com.niit.uritsolution.controller;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.niit.uritsolution.dao.PostDao;
import com.niit.uritsolution.model.Comment;
import com.niit.uritsolution.model.Post;
import com.niit.uritsolution.model.Responce;
import com.niit.uritsolution.model.User;

@RestController
public class PostController {

	@Autowired
	PostDao postDao;
	
	@Autowired
	HttpServletRequest request;

	@RequestMapping("/save/post")
	public ResponseEntity<Post> savePost(@RequestBody Post post) {
		System.out.println("------------------Save Post Handler reached.------------------");
		post.setDate(new Date());
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
	
	@RequestMapping(value = "image/upload/post/{id}", method = RequestMethod.POST)
	// @Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<?> imageUpload(HttpServletRequest req, @PathVariable("id") int id) {
		/*
		 * Image Upload functionality.
		 */
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		Iterator<String> itr = mr.getFileNames();
		while (itr.hasNext()) {
			// org.springframework.web.multipart.MultipartFile
			MultipartFile fd = mr.getFile(itr.next());
			String fileName = fd.getOriginalFilename();
			System.out.println("*****" + fileName);

			Post post = postDao.getPostById(id);

			try {
				/*
				 * Creating the directory in the server context to store.
				 */
				String imagePath = request.getServletContext().getRealPath("/resources/images/posts");
				System.out.println("------- Context Path set -------");
				File dir = new File(imagePath + File.separator);
				System.out.println("------- Directory set to" + dir + "-------");
				if (!dir.exists())
					dir.mkdirs();
				int orgName = post.getId();
				String filePath = imagePath + File.separator + orgName + ".jpg";
				File dest = new File(filePath);
				System.out.println("------- Image uploaded to " + dest + "-------");
				fd.transferTo(dest);

			} catch (Exception e) {
				System.out.println("You failed to upload " + post.getId() + " => " + e.getMessage());
			}

		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
