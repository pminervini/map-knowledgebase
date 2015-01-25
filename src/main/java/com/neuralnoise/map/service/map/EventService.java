package com.neuralnoise.map.service.map;

import java.util.List;

import org.joda.time.DateTime;

import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.service.map.util.IContributedEntityService;

public interface EventService extends IContributedEntityService<Event> {

	public List<Event> findBetween(DateTime startDate, DateTime endDate);
	
}
