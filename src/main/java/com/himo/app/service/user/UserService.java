package com.himo.app.service.user;

import java.util.List;

import com.himo.app.entity.user.User;

public interface UserService
{
	List<User> findAll();
	
	User getUserByUserName(String userName);
	
	void update(User user);
	
	void save(User newUser);
}
