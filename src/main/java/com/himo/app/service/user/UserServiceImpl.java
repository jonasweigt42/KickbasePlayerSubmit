package com.himo.app.service.user;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himo.app.entity.user.User;
import com.himo.app.entity.user.UserDao;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserDao dao;

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
	public User getUserByUserName(String mailAddress)
	{
		List<User> users = dao.findAll();

		for (User user : users)
		{
			if (user.getUserName().equals(mailAddress))
			{
				return user;
			}
		}
		return null;
	}

	@Override
	public void update(User user)
	{
		dao.update(user);
	}

	@Override
	public void save(User newUser)
	{
		dao.save(newUser);
	}

}
