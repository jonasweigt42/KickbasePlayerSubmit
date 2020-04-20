package com.himo.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.himo.app.entity.user.User;

@Repository
public abstract class GenericDao<T extends Serializable>
{

	private Class<T> clazz;

	@PersistenceContext
	EntityManager entityManager;

	public void setClazz(Class<T> clazzToSet)
	{
		this.clazz = clazzToSet;
	}

	@Transactional
	public List<T> findAll()
	{
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	@Transactional
	public void save(T entity)
	{
		entityManager.persist(entity);
	}

	@Transactional
	public void update(T entity)
	{
		entityManager.merge(entity);
	}

	@Transactional
	public void delete(T entity)
	{
		entityManager.remove(entity);
	}
}
