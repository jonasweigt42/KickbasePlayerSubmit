package com.himo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himo.app.entity.user.User;
import com.himo.app.entity.user.UserDao;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getUsers()
	{
		return userDao.getUsers();
	}

}
