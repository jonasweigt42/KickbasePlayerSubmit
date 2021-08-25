package com.himo.app.service.spieltag;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Spieltag findByName(String name)
	{
		Optional<Spieltag> found = findAll().stream().filter(s -> s.getName().equals(name)).findFirst();
		if (found.isPresent())
		{
			return found.get();
		}
		return null;
	}

	@Override
	public Spieltag findById(int id)
	{
		Optional<Spieltag> found = findAll().stream().filter(s -> s.getId() == id).findFirst();
		if (found.isPresent())
		{
			return found.get();
		}
		return null;
	}

}
