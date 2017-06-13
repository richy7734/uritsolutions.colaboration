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
	public List<User> listUser() {
		
		return sessionFactory.getCurrentSession().createQuery("FROM User WHERE enabled = '"+true+"'",User.class).list();
	}

	@Override
	public User getUserById(int id) {
		
		return sessionFactory.getCurrentSession().get(User.class, id);
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
		
		sessionFactory.getCurrentSession().update(friends);
		Friends tmpFrnds = new Friends();
		tmpFrnds.setName(user.getName());
		tmpFrnds.setStatus(friends.getStatus());
		tmpFrnds.setUserId(friends.getFrndId());
		tmpFrnds.setFrndId(friends.getUserId());
		sessionFactory.getCurrentSession().save(tmpFrnds);
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
		
		return sessionFactory.getCurrentSession().createQuery("FROM User WHERE enabled = '"+false+"'",User.class).list();
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
