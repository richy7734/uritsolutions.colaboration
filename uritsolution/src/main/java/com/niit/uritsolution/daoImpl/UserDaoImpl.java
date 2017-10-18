package com.niit.uritsolution.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.uritsolution.dao.UserDao;
import com.niit.uritsolution.model.Friends;
import com.niit.uritsolution.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public void setAdmin() {
		User user = new User();
		user.setEmail("richy7734.rprs@gmail.com");
		user.setEnabled(true);
		user.setGender("male");
		user.setName("Paul Richard");
		user.setOnlineStatus(true);
		user.setPassword("jesus7734");
		user.setPhone("9489547615");
		user.setRole("ROLE_ADMIN");
		user.setUsername("admin");
		
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
	
	public boolean addUser(User user) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		
		try{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void deleteUser(User user) {
		
	}

	@Override
	public List<User> listUser(User user) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM User WHERE enabled = '"+1+"' AND id != '"+user.getId()+"' ",User.class).list();
	}

	@Override
	public User getUserById(int id) {
		
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public User getUserByUsername(String usernaem) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM User WHERE username = '"+usernaem+"'", User.class).getSingleResult();
	}
	
	@Override
	public User validate(String username, String password) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM User WHERE username = '"+username+"' AND password = '"+password+"'",User.class).getSingleResult();
	}

	@Override
	public List<User> friendSearch(String name) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM User WHERE name = '"+name+"'", User.class).getResultList();
	}

	@Override
	public void sendFriendRequest(Friends friends) {
		
		sessionFactory.getCurrentSession().save(friends);
	}

	@Override
	public void acceptFriendRequest(Friends friends, User user) {
		
		friends.setFriend(user);
		friends.setStatus("");
	}

	@Override
	public List<Friends> getFriendsList(int userId) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Friends WHERE userId = '"+userId+"'", Friends.class).getResultList();
	}

	@Override
	public Friends getFriendById(int id) {
		return sessionFactory.getCurrentSession().get(Friends.class, id);
	}
	
	@Override
	public List<User> listUserAdmin() {
		
		return sessionFactory.getCurrentSession().createQuery("FROM User WHERE enabled = '"+0+"'",User.class).getResultList();
	}

	@Override
	public boolean checkUser(User user) {
		User tempUser = new User();
		try{
			tempUser = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username = '"+user.getUsername()+"' OR email = '"+user.getEmail()+"'", User.class).getSingleResult();
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}
