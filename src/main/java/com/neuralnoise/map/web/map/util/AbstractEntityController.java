package com.neuralnoise.map.web.map.util;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neuralnoise.map.model.BaseEntity;
import com.neuralnoise.map.service.map.util.IEntityService;

public class AbstractEntityController<EntityType extends BaseEntity, ServiceType extends IEntityService<EntityType>> {

	private static final Logger log = LoggerFactory.getLogger(AbstractEntityController.class);

	@Autowired
	protected ServiceType service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public EntityType create(@RequestBody EntityType entity, HttpServletResponse response) throws Exception {
		log.info("Creating new entity {}", entity);
		EntityType ret = service.create(entity);
		log.info("Created entity: " + ret);
		response.setStatus(HttpStatus.CREATED.value());
		return ret;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<EntityType> list(HttpServletResponse response) {
		log.info("Listing entities");
		response.setStatus(HttpStatus.FOUND.value());
		return service.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	EntityType read(@PathVariable("id") Long id, HttpServletResponse response) {
		log.info("Reading entity with id {}", id);
		EntityType entity = service.getById(id);
		Validate.isTrue(entity != null, "Unable to find entity with id: " + id);
		response.setStatus(HttpStatus.FOUND.value());
		return entity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		log.info("Deleting entity with id {}", id);
		service.deleteById(id);
		response.setStatus(HttpStatus.OK.value());
	}

}