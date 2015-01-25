package com.neuralnoise.map.web.map;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.Annotation;
import com.neuralnoise.map.model.map.ContributedEntity;
import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.service.map.ArtisanService;
import com.neuralnoise.map.service.map.EventService;
import com.neuralnoise.map.service.map.MuseumService;
import com.neuralnoise.map.service.map.OrganizationService;
import com.neuralnoise.map.web.util.Feature;

@Controller
@RequestMapping("/entity")
public class EntityController {

	private static final Logger log = LoggerFactory.getLogger(EntityController.class);

	@Autowired
	private ArtisanService artisanService;
	@Autowired
	private EventService eventService;
	@Autowired
	private MuseumService museumService;
	@Autowired
	private OrganizationService organizationService;

	private static boolean isParameter(HttpServletRequest request, String name) {
		final String parameter = request.getParameter(name);
		return (parameter != null) && Boolean.parseBoolean(parameter);
	}
	
	private static DateTime dateTimeParameter(HttpServletRequest request, String name) {
		final String parameter = request.getParameter(name);
		return (parameter != null ? DateTime.parse(parameter) : null);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<ContributedEntity> get(HttpServletRequest request, HttpServletResponse response) {
		log.info("Request: " + request.getParameterMap());
		
		boolean isArtisans = isParameter(request, "artisans"), isEvents = isParameter(request, "events"),
				isMuseums = isParameter(request, "museums"), isOrganizations = isParameter(request, "organizations");
		
		DateTime startDate = dateTimeParameter(request, "startDate"), endDate = dateTimeParameter(request, "endDate");
		
		log.info("Artisans: {}, Events: {}, Museums: {}, Organizations: {}, StartDate: {}, EndDate: {}", isArtisans, isEvents, isMuseums, isOrganizations, startDate, endDate);
		
		List<ContributedEntity> entities = Lists.newLinkedList();
		
		if (isArtisans) {
			entities.addAll(artisanService.getAll());
		}
		
		if (isEvents) {
			List<Event> events = null;
			if (startDate == null && endDate == null) {
				events = eventService.getAll();
			} else {
				events = eventService.findBetween(startDate, endDate);
			}
			entities.addAll(events);
		}
		
		if (isMuseums) {
			entities.addAll(museumService.getAll());
		}
		
		if (isOrganizations) {
			entities.addAll(organizationService.getAll());
		}
		
		response.setStatus(HttpStatus.OK.value());
		
		return entities;
	}

	@RequestMapping(value = "/feature", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Feature> features(HttpServletRequest request, HttpServletResponse response) {
		List<ContributedEntity> entities = get(request, response);
		List<Feature> features = Lists.newLinkedList();

		for (ContributedEntity entity : entities) {
			Location location = entity.getLocation();
			
			Map<String, Object> properties = entity.getProperties();
			
			Set<String> annotations = Sets.newTreeSet();
			for (Annotation a : entity.getAnnotations()) {
				annotations.add(a.getName());
			}
			
			properties.put("annotations", annotations);
			
			Feature feature = new Feature(properties, (location != null ? location.getPoint() : null));
			features.add(feature);
		}
		return features;
	}
}
