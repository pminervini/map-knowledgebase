package com.neuralnoise.map.web.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.service.map.EventService;
import com.neuralnoise.map.web.map.util.AbstractContributedEntityController;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractContributedEntityController<Event, EventService> {

	private static final Logger log = LoggerFactory.getLogger(EventController.class);

}