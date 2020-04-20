package com.himo.app.service.travel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himo.app.entity.GenericDao;
import com.himo.app.entity.travel.TravelOpportunity;

@Service
public class TravelOpportunityServiceImpl implements TravelOpportunityService
{

	@Autowired
	private GenericDao<TravelOpportunity> dao;
	
	@PostConstruct
	public void init()
	{
		dao.setClazz(TravelOpportunity.class);
	}
	
	@Override
	public List<TravelOpportunity> findAll()
	{
		return dao.findAll();
	}

	@Override
	public List<String> getTravelOpportunityNames()
	{
		List<String> result = new ArrayList<>();
		List<TravelOpportunity> travels = dao.findAll();
		for(TravelOpportunity t : travels)
		{
			result.add(t.getName());
		}
		return result;
	}
	

}
