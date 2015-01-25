package com.neuralnoise.map.service.map.util;

import java.util.List;

import com.neuralnoise.map.model.BaseEntity;

public interface IEntityService<T extends BaseEntity> {

	public T getById(Long id);

	public T create(T entity) throws Exception;

	public List<T> getAll();

	public void deleteById(Long id);

}
