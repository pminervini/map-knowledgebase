package com.neuralnoise.map.data;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.NamedEntity;

public class AbstractNamedDAO<T extends NamedEntity, I> extends AbstractDAO<T, I> {

	private static final Logger log = LoggerFactory.getLogger(AbstractNamedDAO.class);

	public AbstractNamedDAO(Class<T> clazzToSet) {
		super(clazzToSet);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByName(String name) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where e.name = :name");
		return query.setParameter("name", name).getResultList();
	}

}
