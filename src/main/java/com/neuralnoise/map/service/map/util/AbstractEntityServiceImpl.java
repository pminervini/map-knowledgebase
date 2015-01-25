package com.neuralnoise.map.service.map.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.AbstractDAO;
import com.neuralnoise.map.model.BaseEntity;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.security.SecurityService;

public abstract class AbstractEntityServiceImpl<T extends BaseEntity, D extends AbstractDAO<T, Long>> implements IEntityService<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractContributedEntityServiceImpl.class);

	@Autowired
	protected D entityDAO;

	@Autowired
	protected SecurityService securityService;

	@Override
	@Transactional(readOnly = false)
	public T create(T event) throws Exception {
		boolean authorized = false;
		UserEntity ue = securityService.current();
		if (ue != null) {
			authorized = true;
		}
		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}
		return entityDAO.create(event);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAll() {
		return entityDAO.getAll();
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		boolean authorized = false;
		UserEntity ue = securityService.current();
		if (ue != null) {
			authorized = true;
		}
		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}
		entityDAO.deleteById(id);
	}

	@Override
	public T getById(Long id) {
		return entityDAO.getById(id);
	}

}
