package com.himo.app.entity.spieltag;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.himo.app.entity.GenericDao;

@Repository
public class SpieltagDao extends GenericDao<Spieltag>
{
	@PostConstruct
	public void init()
	{
		setClazz(Spieltag.class);
	}
}