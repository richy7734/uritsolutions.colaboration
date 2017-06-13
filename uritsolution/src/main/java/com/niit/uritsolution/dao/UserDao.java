package com.niit.uritsolution.dao;

import java.util.List;

import com.niit.uritsolution.model.Friends;
import com.niit.uritsolution.model.User;

public interface UserDao {

	public boolean addUser(User user);
	public boolean updateUser(User user);
	public void deleteUser(User user);
	public User getUserById(int id);
	public List<User> listUser();
	public User validate(String username, String password);
	public List<User> friendSearch(String name);
	public void sendFriendRequest(Friends friends);
	public void acceptFriendRequest(Friends friends,User user);
	public List<Friends> getFriendsList(int userId);
	public Friends getFriendById(int id);
	public List<User> listUserAdmin();
	public boolean checkUser(User user);
	
}
