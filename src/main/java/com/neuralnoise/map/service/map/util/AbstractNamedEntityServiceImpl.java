package com.neuralnoise.map.service.map.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.AbstractNamedDAO;
import com.neuralnoise.map.model.NamedEntity;

public abstract class AbstractNamedEntityServiceImpl<T extends NamedEntity, D extends AbstractNamedDAO<T, Long>> extends AbstractEntityServiceImpl<T, D> implements INamedEntityService<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractNamedEntityServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<T> findByName(String name) {
		return entityDAO.findByName(name);
	}

}
