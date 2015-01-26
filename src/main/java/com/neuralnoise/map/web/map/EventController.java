package com.neuralnoise.map.web.map;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.service.map.EventService;
import com.neuralnoise.map.web.map.util.AbstractContributedEntityController;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractContributedEntityController<Event, EventService> {

	private static final Logger log = LoggerFactory.getLogger(EventController.class);

	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public @ResponseBody
	DateTime test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date date = new Date();
		DateTime dateTime = new DateTime(date.getTime());
		
		Event event = new Event();
		event.setName("ABC");
		event.setDescription("DEF");
		event.setStartDate(dateTime);
		event.setEndDate(dateTime);
		
		Location location = new Location();
		location.setAddress("via Cifariello 17, Molfetta");
		
		event.setLocation(location);
		
		this.create(event, response);
		
		response.setStatus(HttpStatus.FOUND.value());
		return dateTime;
	}
	
}