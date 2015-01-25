package com.neuralnoise.map.service.map.util;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.neuralnoise.geo.GeoLocationUtils;
import com.neuralnoise.map.data.map.AbstractContributedDAO;
import com.neuralnoise.map.data.map.AnnotationDAO;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.Annotation;
import com.neuralnoise.map.model.map.ContributedEntity;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.geo.GeoLocationService;
import com.neuralnoise.map.service.geo.GeoLocationService.ServiceType;
import com.vividsolutions.jts.geom.Point;

public abstract class AbstractContributedEntityServiceImpl<T extends ContributedEntity, D extends AbstractContributedDAO<T, Long>> extends AbstractNamedEntityServiceImpl<T, D> implements IContributedEntityService<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractContributedEntityServiceImpl.class);
	
	//@Autowired
	//protected GeoLocationService geoLocationService;
	
	@Autowired
	protected AnnotationDAO annotationDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<T> getAll() {
		//return entityDAO.getAllWithAnnotations();
		return entityDAO.getAll();
	}
	
	@Override
	@Transactional(readOnly = false)
	public T create(T entity) throws Exception {
		boolean authorized = false;

		UserEntity ue = securityService.current();

		if (ue != null) {
			authorized = true;
		}

		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}

		entity.setContributor(ue);
		
		Set<Annotation> persistentAnnotations = Sets.newHashSet();
		
		if (entity.getAnnotations() != null) {
			for (Annotation annotation : entity.getAnnotations()) {
				//annotationDAO.merge(annotation);
				if (annotation != null && annotation.getName() != null) {
					Annotation persistent = annotationDAO.getById(annotation.getName());
					if (persistent == null) {
						persistentAnnotations.add(annotationDAO.create(annotation));
					} else {
						persistentAnnotations.add(persistent);
					}
				}
			}
		}
		
		entity.setAnnotations(persistentAnnotations);
		
		// Let's check if the location has been specified
		Location location = entity.getLocation();
		
		final String address = location.getAddress();
		final Point point = location.getPoint();

		if (address != null && point == null) {
			//List<Location> locations = geoLocationService.lookup(address, ServiceType.GOOGLE);
			List<Location> locations = GeoLocationUtils.queryGoogle(address, "it");
			
			if (locations.size() > 0) {
				location = locations.get(0);
				entity.setLocation(location);
			}
		}
		
		T ret = entityDAO.create(entity);
		
		return ret;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		boolean authorized = false;

		UserEntity ue = securityService.current();

		if (ue != null) {
			if (ue.isAdmin()) {
				authorized = true;
			} else {
				T e = entityDAO.getById(id);
				if (e != null) {
					if (e.getContributor().getName().equals(ue.getName())) {
						authorized = true;
					}
				}
			}
		}

		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}

		entityDAO.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findByContributor(String name) {
		return entityDAO.findByContributor(name);
	}

}
