package com.neuralnoise.map.service.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neuralnoise.map.data.map.MuseumDAO;
import com.neuralnoise.map.model.map.Museum;
import com.neuralnoise.map.service.map.util.AbstractContributedEntityServiceImpl;

@Service
public class MuseumServiceImpl extends AbstractContributedEntityServiceImpl<Museum, MuseumDAO> implements MuseumService {

	private static final Logger log = LoggerFactory.getLogger(MuseumServiceImpl.class);

}
