package com.niit.uritsolution.controller;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.uritsolution.config.EmailUtil;
import com.niit.uritsolution.dao.ForumDao;
import com.niit.uritsolution.dao.PostDao;
import com.niit.uritsolution.dao.UserDao;
import com.niit.uritsolution.model.Forum;
import com.niit.uritsolution.model.People;
import com.niit.uritsolution.model.Post;
import com.niit.uritsolution.model.User;

@RestController
public class ForumController {

	@Autowired
	ForumDao forumDao;

	@Autowired
	UserDao userDao;

	@Autowired
	PostDao postDao;

	@RequestMapping("create/forum/{userId}")
	public ResponseEntity<People> createForum(@RequestBody Forum forum, @PathVariable("userId") int userId) {
		User user = userDao.getUserById(userId);
		forum.setAdminId(userId);
		forumDao.createGroup(forum);
		People people = new People();
		people.setGroup(forum);
		people.setName(user.getName());
		people.setRole("ADMIN");
		people.setUserId(user.getId());
		people.setUsername(user.getUsername());
		people.setEnabled(true);
		people.setGrpId(forum.getId());
		people.setForumName(forum.getName());

		people = forumDao.createForum(people);

		return new ResponseEntity<People>(people, HttpStatus.OK);
	}

	@RequestMapping("join/forum/{frmId}")
	public ResponseEntity<People> joinForum(@RequestBody User user, @PathVariable("frmId") int frmId) {
		Forum forum = forumDao.getForumById(frmId);
		People people = new People();
		if(forumDao.checkPeople(frmId, user.getId())){
			return new ResponseEntity<People>(people, HttpStatus.LOCKED);
		}
		people.setForumName(forum.getName());
		people.setGroup(forum);
		people.setName(user.getName());
		people.setRole("MEMBER");
		people.setUserId(user.getId());
		people.setUsername(user.getUsername());
		people.setEnabled(false);
		people.setGrpId(forum.getId());

		people = forumDao.createForum(people);

		return new ResponseEntity<People>(people, HttpStatus.OK);
	}

	@RequestMapping("approve/member/{id}")
	public List<People> approveMember(@PathVariable("id") int id) {
		People people = forumDao.getPeopleById(id);
		people.setEnabled(true);
		User user = userDao.getUserById(people.getUserId());
		forumDao.acceptRequest(people);
		
		final String fromEmail = "richy7734.rprs@gmail.com";

		final String password = "jesus7734";
		final String toEmail = user.getEmail();

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port

		Authenticator auth = new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		EmailUtil.sendEmail(session, toEmail, "UrItSolutions Forum Approval", "Congradulations " + user.getName()
				+ "....!!! You have joined the forum " + people.getGroup().getName());

		return forumDao.getPeopleForApproval(people.getGrpId());
	}

	@RequestMapping("get/myId/{userId}/{grpName}")
	public ResponseEntity<People> getMyId(@PathVariable("userId") int userId, @PathVariable("grpName") String grpName) {
		Forum forum = forumDao.getGroupByName(grpName);
		People people = forumDao.getPeopleByIdAndGrp(userId, forum);
		return new ResponseEntity<People>(people, HttpStatus.OK);
	}

	@RequestMapping("get/peoples/by/currentUser/{userId}")
	public List<People> getPeopleByCurrentUser(@PathVariable("userId") int userId) {
		return forumDao.getPeopleByCurrentuser(userId);
	}

	@RequestMapping("forum/post/{frmId}")
	public ResponseEntity<?> forumPost(@RequestBody Post post, @PathVariable("frmId") int frmId) {
		post.setGrpId(frmId);
		post.setCategory("FORUM");
		post.setDate(new Date());
		postDao.addPost(post);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping("get/post/list/{frmId}")
	public List<Post> getPostList(@PathVariable("frmId") int frmId) {
		return postDao.getPostListByFrmId(frmId);
	}

	@RequestMapping("get/post/by/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable("id") int id) {
		Post post = postDao.getPostById(id);

		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}

	@RequestMapping("get/forums")
	public List<Forum> getForum() {
		return forumDao.listForum();
	}

	@RequestMapping("get/people/for/approval/{grpId}")
	public List<People> getPeopleForApproval(@PathVariable("grpId") int grpId) {
		System.out.println("Fetching people for approval..!!!");
		return forumDao.getPeopleForApproval(grpId);
	}

	@RequestMapping("enter/forum/{id}")
	public ResponseEntity<People> enterForum(@PathVariable("id") int id) {
		People people = forumDao.getPeopleById(id);
		Forum forum = forumDao.getForumById(people.getGrpId());
		people.setGroup(forum);
		if(people.isEnabled() == false){
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}
		return new ResponseEntity<People>(people, HttpStatus.OK);
	}
}
