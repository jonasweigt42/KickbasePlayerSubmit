package com.himo.app.service.submit;

import java.util.List;
import java.util.Optional;

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

	@Override
	public PlayerSubmit find(String userName, String spieltag, String saison)
	{
		Optional<PlayerSubmit> submitOptional = dao.findAll().stream().filter(s -> s.getUserName().equals(userName))
				.filter(s -> s.getSpieltag().equals(spieltag)).filter(s -> s.getSaison().equals(saison)).findAny();
		if (submitOptional.isPresent())
		{
			return submitOptional.get();
		}
		return null;
	}

	@Override
	public PlayerSubmit find(String userName, String spieltag, String spielerName, String saison)
	{
		Optional<PlayerSubmit> submitOptional = dao.findAll().stream().filter(s -> s.getUserName().equals(userName))
				.filter(s -> s.getSpieltag().equals(spieltag)).filter(s -> s.getSaison().equals(saison))
				.filter(s -> s.getPlayerName().equals(spielerName)).findAny();
		if (submitOptional.isPresent())
		{
			return submitOptional.get();
		}
		return null;
	}


}
