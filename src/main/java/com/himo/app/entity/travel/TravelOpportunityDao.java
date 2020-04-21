package com.himo.app.entity.travel;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.himo.app.entity.GenericDao;

@Repository
public class TravelOpportunityDao extends GenericDao<TravelOpportunity>
{

	@PostConstruct
	public void init()
	{
		setClazz(TravelOpportunity.class);
	}
}
