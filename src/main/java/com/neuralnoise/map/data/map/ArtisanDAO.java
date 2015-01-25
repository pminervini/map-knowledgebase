package com.neuralnoise.map.data.map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.map.Artisan;

@Repository
@Transactional
public class ArtisanDAO extends AbstractContributedDAO<Artisan, Long> {

	private static final Logger log = LoggerFactory.getLogger(ArtisanDAO.class);

	public ArtisanDAO() {
		super(Artisan.class);
	}

}
