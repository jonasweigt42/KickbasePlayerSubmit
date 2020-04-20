package com.himo.app.entity.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao
{

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public List<User> getUsers()
	{
		Session session = entityManager.unwrap(Session.class);
		CriteriaQuery<User> criteria = session.getCriteriaBuilder().createQuery(User.class);

		criteria.from(User.class);
		
		return session.createQuery(criteria).getResultList();
	}

	@Transactional
	@Override
	public User getUserByUserName(String userName)
	{
		Session session = entityManager.unwrap(Session.class);
		CriteriaQuery<User> criteria = session.getCriteriaBuilder().createQuery(User.class);

		criteria.from(User.class);
		
		List<User> users = session.createQuery(criteria).getResultList();
		
		for(User user : users)
		{
			if(user.getUserName().equals(userName))
			{
				return user;
			}
		}
		return null;
	}

	
	@Transactional
	@Override
	public void save(User user)
	{
		Session session = entityManager.unwrap(Session.class);

		session.save(user);
	}
}
