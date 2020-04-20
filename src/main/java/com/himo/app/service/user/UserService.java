package com.himo.app.service.user;

import java.util.List;

import com.himo.app.entity.user.User;

public interface UserService
{
	List<User> getUsers();
	
	User getUserByUserName(String userName);
}
