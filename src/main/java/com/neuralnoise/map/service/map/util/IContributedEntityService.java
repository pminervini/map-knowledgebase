package com.neuralnoise.map.service.map.util;

import java.util.List;

import com.neuralnoise.map.model.map.ContributedEntity;

public interface IContributedEntityService<T extends ContributedEntity> extends INamedEntityService<T> {

	public List<T> findByContributor(String name);

}
