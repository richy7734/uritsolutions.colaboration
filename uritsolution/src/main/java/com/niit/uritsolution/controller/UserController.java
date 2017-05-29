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
import com.niit.uritsolution.model.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;
	
	@RequestMapping("all/users")
	public List<User> getUserList(){
		return userDao.listUser();
	}
	
	@RequestMapping("add/user")
	public ResponseEntity<User> addUser(@RequestBody User user){
		userDao.addUser(user);
		System.out.println("User : "+user.getName()+" registerded sucessfully.");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping("login")
	public ResponseEntity<User> login(@RequestBody User user, HttpSession session){
		user = userDao.validate(user.getEmail(), user.getPassword());
		
		if(user == null){
			user = new User();
			user.setErrorCode("404");
			user.setError("Sorry Wrong credentials or user does not exists : "+user.getName());
		}else{
			user.setErrorCode("200");
			user.setError("Hello "+user.getName()+". Logged in successfully...!!");
			user.setOnlineStatus(true);
			userDao.updateUser(user);
			
			session.setAttribute("userLoggedin", user.getId());
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	/*
	 * Friends module....!!!
	 * */
	
	 
}