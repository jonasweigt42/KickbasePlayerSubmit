package com.himo.app.entity.user;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.himo.app.entity.GenericDao;

@Repository
public class UserDao extends GenericDao<User>
{

	@PostConstruct
	public void init()
	{
		setClazz(User.class);
	}
	
	@Transactional
	public User getUserByUserName(String userName)
	{
		List<User> users = findAll();
		
		for(User user : users)
		{
			if(user.getUserName().equals(userName))
			{
				return user;
			}
		}
		return null;
	}

}
