package com.himo.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenericDao<T extends Serializable>
{

	private Class<T> clazz;

	@PersistenceContext
	EntityManager entityManager;
	
	public void setClazz(Class<T> clazzToSet)
	{
		this.clazz = clazzToSet;
	}

	@SuppressWarnings("unchecked")
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
