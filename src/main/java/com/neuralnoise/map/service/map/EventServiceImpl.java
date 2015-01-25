package com.neuralnoise.map.service.map;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neuralnoise.map.data.map.EventDAO;
import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.service.map.util.AbstractContributedEntityServiceImpl;

@Service
public class EventServiceImpl extends AbstractContributedEntityServiceImpl<Event, EventDAO> implements EventService {

	private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

	@Override
	public List<Event> findBetween(DateTime startDate, DateTime endDate) {
		/*
		boolean authorized = false;
		UserEntity ue = securityService.current();

		if (ue != null) {
			authorized = true;
		}
		
		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}
		*/
		return entityDAO.findBetween(startDate, endDate);
	}

}
