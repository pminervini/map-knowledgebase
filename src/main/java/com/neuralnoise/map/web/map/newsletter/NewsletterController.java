package com.neuralnoise.map.web.map.newsletter;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.map.newsletter.Subscription;
import com.neuralnoise.map.service.map.newsletter.NewsletterService;

@Controller
@RequestMapping("/newsletter")
public class NewsletterController {

	private static final Logger log = LoggerFactory.getLogger(NewsletterController.class);
	
	@Autowired
	protected NewsletterService service;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Subscription create(@ModelAttribute Subscription entity, HttpServletResponse response) {
		log.info("Creating new entity {}", entity);
		Subscription ret = service.create(entity);
		log.info("Created entity: " + ret);
		response.setStatus(HttpStatus.CREATED.value());
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Subscription> list(HttpServletResponse response) {
		log.info("Listing entities");
		response.setStatus(HttpStatus.FOUND.value());
		return service.getAll();
	}
	
}
