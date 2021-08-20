package com.himo.app.service.submit;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himo.app.entity.submit.PlayerSubmit;
import com.himo.app.entity.submit.PlayerSubmitDao;

@Service
public class PlayerSubmitServiceImpl implements PlayerSubmitService
{
	@Autowired
	private PlayerSubmitDao dao;

	@PostConstruct
	public void init()
	{
		dao.setClazz(PlayerSubmit.class);
	}

	
	@Override
	public List<PlayerSubmit> findAll()
	{
		return dao.findAll();
	}

	@Override
	public void update(PlayerSubmit submit)
	{
		dao.update(submit);
	}

	@Override
	public void save(PlayerSubmit submit)
	{
		dao.save(submit);
	}

}
