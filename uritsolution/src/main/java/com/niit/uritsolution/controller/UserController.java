package com.niit.uritsolution.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.niit.uritsolution.config.EmailUtil;
import com.niit.uritsolution.dao.UserDao;
import com.niit.uritsolution.model.Friends;
import com.niit.uritsolution.model.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;

	@Autowired
	HttpServletRequest request;

	@RequestMapping("all/users")
	public List<User> getUserList(@RequestBody User user) {
		return userDao.listUser(user);
	}


	@RequestMapping("get/user/by/id")
	public ResponseEntity<User> getUserById(@RequestBody int id) {
		User user = userDao.getUserById(id);
		System.out.println("User : " + user.getName() + " fetched sucessfully.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping("add/user")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		System.out.println("------------------- Register User Reached -------------------");
		if(userDao.checkUser(user)){
			return new ResponseEntity<User>(user, HttpStatus.LOCKED);
		}
		user.setEnabled(false);
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
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		} else {
			if (user.isEnabled() == false) {
				user = new User();
				user.setErrorCode("420");
				user.setError("User not aproved : " + user.getName());
				return new ResponseEntity<User>(user, HttpStatus.LOCKED);
			} else {
				user.setErrorCode("200");
				user.setError("Hello " + user.getName() + ". Logged in successfully...!!");
				user.setOnlineStatus(true);
				userDao.updateUser(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		}

	}

	@RequestMapping(value = "image/upload/{id}", method = RequestMethod.POST)
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

			User user = userDao.getUserById(id);

			try {
				/*
				 * Creating the directory in the server context to store.
				 */
				String imagePath = request.getServletContext().getRealPath("/resources/images");
				System.out.println("------- Context Path set -------");
				File dir = new File(imagePath + File.separator);
				System.out.println("------- Directory set to" + dir + "-------");
				if (!dir.exists())
					dir.mkdirs();
				int orgName = user.getId();
				String filePath = imagePath + File.separator + orgName + ".jpg";
				File dest = new File(filePath);
				System.out.println("------- Image uploaded to " + dest + "-------");
				fd.transferTo(dest);

			} catch (Exception e) {
				System.out.println("You failed to upload " + user.getId() + " => " + e.getMessage());
			}

		}

		return new ResponseEntity<>(HttpStatus.OK);
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
	public ResponseEntity<Friends> frndRequest(@RequestBody Friends friend) {
		
		
		
		return new ResponseEntity<Friends>(friend, HttpStatus.OK);
	}

	@RequestMapping("get/friend/request")
	public List<Friends> getFriendRequest(@RequestBody User user, HttpSession session) {

		return userDao.getFriendsList(user.getId());
	}

	@RequestMapping("friend/accept/{currentUserId}")
	public ResponseEntity<Friends> frndRequestAccept(@RequestBody int id,@PathVariable("currentUserId") int currentUserId) {
		
		User user = new User();
		user = userDao.getUserById(currentUserId);
		Friends friends = userDao.getFriendById(id);
		friends.setStatus("accepted");
		userDao.acceptFriendRequest(friends,user);

		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}

	/*
	 * Admin Controller
	 */

	@RequestMapping("get/users/admin")
	public List<User> getFrndsAdmin() {
		return userDao.listUserAdmin();
	}

	@RequestMapping("approve/user/{id}")
	public List<User> approveUser(@PathVariable("id") int id) {
		User user = userDao.getUserById(id);
		user.setEnabled(true);
		userDao.updateUser(user);
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
		EmailUtil.sendEmail(session, toEmail, "UrItSolutions Approval", "Congradulations "+user.getName()+"....!!! You have been Approved by the Admin");

				
		return userDao.listUserAdmin();
	}
}
