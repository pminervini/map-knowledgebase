package com.neuralnoise.map.service.map.util;

import java.util.List;

import com.neuralnoise.map.model.NamedEntity;

public interface INamedEntityService<T extends NamedEntity> extends IEntityService<T> {

	public List<T> findByName(String name);

}
