package com.himo.app.service.user;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himo.app.entity.GenericDao;
import com.himo.app.entity.user.User;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private GenericDao<User> dao;

	@PostConstruct
	public void init()
	{
		dao.setClazz(User.class);
	}

	@Override
	public List<User> findAll()
	{
		return dao.findAll();
	}

	@Override
	public User getUserByUserName(String userName)
	{
		List<User> users = dao.findAll();

		for (User user : users)
		{
			if (user.getUserName().equals(userName))
			{
				return user;
			}
		}
		return null;
	}

}
