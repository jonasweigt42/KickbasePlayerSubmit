package com.himo.app.service.stats;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himo.app.entity.spieltag.Spieltag;
import com.himo.app.entity.submit.PlayerSubmit;
import com.himo.app.service.spieltag.SpieltagService;
import com.himo.app.service.submit.PlayerSubmitService;

@Service
public class StatsServiceImpl implements StatsService
{
	
	@Autowired
	private PlayerSubmitService playerSubmitService;
	
	@Autowired
	private SpieltagService spieltagService;

	@Override
	public List<PlayerSubmit> getAllBeforeNow()
	{
		List<PlayerSubmit> all = playerSubmitService.findAll();
		List<PlayerSubmit> result = new ArrayList<>();
		for(PlayerSubmit submit : all)
		{
			Spieltag spieltag = spieltagService.findByName(submit.getSpieltag());
			if(LocalDateTime.now().isAfter(spieltag.getStartDateTime()))
			{
				result.add(submit);
			}
		}
		return result;
	}

}
