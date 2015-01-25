package com.neuralnoise.map.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.common.base.Preconditions;

@Repository
public abstract class AbstractDAO<T extends Serializable, I> {

	private static final Logger log = LoggerFactory.getLogger(AbstractDAO.class);

	protected final Class<T> clazz;

	@PersistenceContext
	protected EntityManager entityManager;

	public AbstractDAO(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T getById(final I id) {
		Preconditions.checkArgument(id != null);
		T e = getEntityManager().find(clazz, id);
		return e;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		// XXX: NOT GOOD - Fetching strategies are ignored if we use the query interface
		//return getEntityManager().createQuery("from " + clazz.getName()).getResultList();
	
		Session session = getEntityManager().unwrap(Session.class);
		Criteria criteria = session.createCriteria(clazz, "entity");
		List<T> list = criteria.list();
		return list;
	}

	public T create(final T entity) {
		log.info("Making entity persistent: " + entity);
		Preconditions.checkNotNull(entity);
		getEntityManager().persist(entity);
		return entity;
	}
	
	public T merge(final T entity) {
		Preconditions.checkNotNull(entity);
		getEntityManager().merge(entity);
		return entity;
	}

	public T update(final T entity) {
		Preconditions.checkNotNull(entity);
		return (T) getEntityManager().merge(entity);
	}

	public void delete(final T entity) {
		Preconditions.checkNotNull(entity);
		getEntityManager().remove(entity);
	}

	public void deleteById(final I entityId) {
		log.info("Deleting by Id: " + entityId);
		final T entity = getById(entityId);
		Preconditions.checkState(entity != null);
		delete(entity);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}