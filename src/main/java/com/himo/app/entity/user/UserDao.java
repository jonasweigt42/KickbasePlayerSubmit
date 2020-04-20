package com.himo.app.entity.user;

import java.util.List;

public interface UserDao
{

	List<User> getUsers();
	
	void save(User user);
	
	User getUserByUserName(String userName);
}

