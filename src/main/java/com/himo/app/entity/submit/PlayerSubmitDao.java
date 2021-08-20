package com.himo.app.entity.submit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.himo.app.entity.GenericDao;

@Repository
public class PlayerSubmitDao extends GenericDao<PlayerSubmit>
{
	@PostConstruct
	public void init()
	{
		setClazz(PlayerSubmit.class);
	}
}
