package com.neuralnoise.map.data.map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.map.Museum;

@Repository
@Transactional
public class MuseumDAO extends AbstractContributedDAO<Museum, Long> {

	private static final Logger log = LoggerFactory.getLogger(MuseumDAO.class);

	public MuseumDAO() {
		super(Museum.class);
	}

}
