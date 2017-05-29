package com.niit.uritsolution.dao;

import java.util.List;

import com.niit.uritsolution.model.User;

public interface UserDao {

	public boolean addUser(User user);
	public boolean updateUser(User user);
	public void deleteUser(User user);
	public User getUserById(int id);
	public List<User> listUser();
	public User validate(String username, String password);
}
