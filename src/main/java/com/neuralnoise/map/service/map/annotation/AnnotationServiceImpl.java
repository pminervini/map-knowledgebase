package com.neuralnoise.map.service.map.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.map.AnnotationDAO;
import com.neuralnoise.map.model.map.Annotation;

@Service
public class AnnotationServiceImpl implements AnnotationService {

	private static final Logger log = LoggerFactory.getLogger(AnnotationServiceImpl.class);

	@Autowired
	protected AnnotationDAO annotationDAO;

	@Override
	@Transactional(readOnly = false)
	public Annotation create(Annotation subscription) {
		return annotationDAO.create(subscription);
	}

}