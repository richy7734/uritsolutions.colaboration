package com.niit.uritsolution.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.uritsolution.dao.UserDao;
import com.niit.uritsolution.model.Friends;
import com.niit.uritsolution.model.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;

	@RequestMapping("all/users")
	public List<User> getUserList() {
		return userDao.listUser();
	}
	
	@RequestMapping("get/user/by/id")
	public ResponseEntity<User> getUserById(@RequestBody int id) {
		User user = userDao.getUserById(id);
		System.out.println("User : " + user.getName() + " fetched sucessfully.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping("add/user")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		userDao.addUser(user);
		System.out.println("User : " + user.getName() + " registerded sucessfully.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping("login")
	public ResponseEntity<User> login(@RequestBody User user, HttpSession session) {
		user = userDao.validate(user.getUsername(), user.getPassword());

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setError("Sorry Wrong credentials or user does not exists : " + user.getName());
		} else {
			user.setErrorCode("200");
			user.setError("Hello " + user.getName() + ". Logged in successfully...!!");
			user.setOnlineStatus(true);
			userDao.updateUser(user);

			session.setAttribute("userLoggedin", user.getId());
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping("logout")
	public ResponseEntity<User> logout(@RequestBody User user, HttpSession session) {

		user.setErrorCode("200");
		user.setError("User " + user.getName() + ". Logged out successfully...!!");
		user.setOnlineStatus(false);
		userDao.updateUser(user);

		session.setAttribute("userLoggedin", null);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/*
	 * Friends module....!!!
	 */

	@RequestMapping("search")
	public List<User> friendSearch(@RequestBody String name, HttpSession session) {

		return userDao.friendSearch(name);
	}

	@RequestMapping("list/friends")
	public List<Friends> friendLIst(@RequestBody User user, HttpSession session) {

		return userDao.getFriendsList(user.getId());
	}

	@RequestMapping("send/friend/request")
	public ResponseEntity<Friends> frndRequest(@RequestBody Friends friends, HttpSession session) {
		User user = userDao.getUserById(friends.getFrndId());
		friends.setName(user.getName());
		friends.setStatus("requested");
		System.out.println("The Friend data is : " + friends.getUserId() + " " + friends.getFrndId() + " "
				+ friends.getName() + " " + friends.getStatus());
		userDao.sendFriendRequest(friends);

		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}

	@RequestMapping("get/friend/request")
	public List<Friends> getFriendRequest(@RequestBody User user, HttpSession session) {

		return userDao.getFriendsList(user.getId());
	}

	@RequestMapping("friend/accept")
	public ResponseEntity<Friends> frndRequestAccept(@RequestBody int id, HttpSession session) {

		Friends friends = userDao.getFriendById(id);
		friends.setStatus("accepted");
		userDao.acceptFriendRequest(friends);

		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
}
