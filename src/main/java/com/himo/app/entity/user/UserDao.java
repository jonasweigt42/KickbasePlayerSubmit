package com.himo.app.entity.user;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.himo.app.entity.GenericDao;

@Repository
public class UserDao extends GenericDao<User>
{
	@PostConstruct
	public void init()
	{
		setClazz(User.class);
	}
}
