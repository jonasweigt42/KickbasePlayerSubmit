package com.himo.app.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himo.app.entity.user.User;
import com.himo.app.entity.user.UserDao;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserDao dao;

	@Override
	public List<User> findAll()
	{
		return dao.findAll();
	}

	@Override
	public User getUserByUserName(String userName)
	{
		return dao.getUserByUserName(userName);
	}

}
