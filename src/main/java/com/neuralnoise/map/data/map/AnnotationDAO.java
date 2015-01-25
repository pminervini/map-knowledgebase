package com.neuralnoise.map.data.map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.neuralnoise.map.data.AbstractDAO;
import com.neuralnoise.map.model.map.Annotation;

@Repository
@Transactional
public class AnnotationDAO extends AbstractDAO<Annotation, String> {

	public AnnotationDAO() {
		super(Annotation.class);
	}

}