package com.himo.app.service.spieltag;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himo.app.entity.spieltag.Spieltag;
import com.himo.app.entity.spieltag.SpieltagDao;

@Service
public class SpieltagServiceImpl implements SpieltagService
{

	@Autowired
	private SpieltagDao dao;

	@PostConstruct
	public void init()
	{
		dao.setClazz(Spieltag.class);
	}

	@Override
	public List<Spieltag> findAll()
	{
		return dao.findAll();
	}

}
