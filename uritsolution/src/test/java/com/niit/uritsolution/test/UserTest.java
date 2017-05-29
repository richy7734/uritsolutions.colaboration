package com.niit.uritsolution.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.uritsolution.dao.UserDao;
import com.niit.uritsolution.model.User;


public class UserTest {

	/*
	 * Get the application context
	 */
	private static AnnotationConfigApplicationContext context;
	private static UserDao userDao;
	private User user;

	/*
	 * Initialize the classes
	 */
	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.uritsolution");
		context.refresh();
		userDao = (UserDao) context.getBean("userDao");
	}

	/*
	 * Test Case for adding a user.
	 */
	/*
	@Test
	public void addUserTest() {
		
		user = new User();

		user.setName("Paul Richard");
		user.setEmail("richy7734.rprs@gmail.com");
		user.setEnabled(true);
		user.setPassword("password");
		user.setPhone("9489547615");
		
		assertEquals("Product Successfully added to the database", true, userDao.addUser(user));

	}
	

	/*
	 * Test Case for Getting User by id.
	 */
	
	/*@Test
	public void userByIdTest() {
		user = new User();
		user = userDao.getUserById(1);
		assertEquals("Product Successfully retrived by id","Paul Richard",user.getName());
	}*/
	
	/*
	 * Test Case for updating a user.
	 * */
	
	/*@Test
	public void updateUserTest() {
		user = new User();
		user = userDao.getUserById(1);
		user.setEnabled(false);
		
		assertEquals("Product Successfully updated in the database", true, userDao.updateUser(user));
	}
	*/
	
}
