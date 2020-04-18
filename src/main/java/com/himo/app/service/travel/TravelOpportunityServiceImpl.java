package com.himo.app.service.travel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himo.app.entity.travel.TravelOpportunity;
import com.himo.app.entity.travel.TravelOpportunityDao;

@Service
public class TravelOpportunityServiceImpl implements TravelOpportunityService
{

	@Autowired
	private TravelOpportunityDao dao;
	
	@Override
	public List<TravelOpportunity> getTravelOpportunities()
	{
		return dao.getTravelOpportunities();
	}

	@Override
	public List<String> getTravelOpportunityNames()
	{
		List<String> result = new ArrayList<>();
		List<TravelOpportunity> travels = dao.getTravelOpportunities();
		for(TravelOpportunity t : travels)
		{
			result.add(t.getName());
		}
		return result;
	}
	

}
