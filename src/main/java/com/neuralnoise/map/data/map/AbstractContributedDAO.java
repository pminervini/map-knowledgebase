package com.neuralnoise.map.data.map;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.data.AbstractNamedDAO;
import com.neuralnoise.map.model.map.ContributedEntity;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

@Repository
public abstract class AbstractContributedDAO<T extends ContributedEntity, I> extends AbstractNamedDAO<T, I> {

	private static final Logger log = LoggerFactory.getLogger(AbstractContributedDAO.class);

	public AbstractContributedDAO(Class<T> clazzToSet) {
		super(clazzToSet);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByContributor(String name) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where e.contributor.name = :name");
		return query.setParameter("name", name).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findNearby(Point centerPoint, Long units) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where dwithin(e.point, :centerPoint, :units) = true", clazz);
		return query.setParameter("centerPoint", centerPoint).setParameter("units", units).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithin(Geometry filter, Long units) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where within(e.point, :filter) = true", clazz);
		return query.setParameter("filter", filter).getResultList();
	}

}
