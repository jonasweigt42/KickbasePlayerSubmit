package com.himo.app.entity.travel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TravelOpportunityDaoImpl implements TravelOpportunityDao
{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public List<TravelOpportunity> getTravelOpportunities()
	{
		Session session = entityManager.unwrap(Session.class);
		CriteriaQuery<TravelOpportunity> criteria = session.getCriteriaBuilder().createQuery(TravelOpportunity.class);

		criteria.from(TravelOpportunity.class);
		
		return session.createQuery(criteria).getResultList();
	}

}
