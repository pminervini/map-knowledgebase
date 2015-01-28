package com.neuralnoise.map.web.map.crowd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.geo.GeoLocationUtils;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.crowd.CrowdContribution;
import com.neuralnoise.map.service.map.crowd.CrowdContributionService;

@Controller
@RequestMapping("/crowd")
public class CrowdContributionController {

	private static final Logger log = LoggerFactory.getLogger(CrowdContributionController.class);
	
	@Autowired
	protected CrowdContributionService service;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public CrowdContribution create(@RequestBody CrowdContribution entity, HttpServletResponse response) {
		log.info("Creating new entity {}", entity);
		CrowdContribution ret = service.create(entity);
		log.info("Created entity: " + ret);
		response.setStatus(HttpStatus.CREATED.value());
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<CrowdContribution> list(HttpServletResponse response) {
		log.info("Listing entities");
		response.setStatus(HttpStatus.FOUND.value());
		return service.getAll();
	}
	
	@RequestMapping(value = { "/test" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Location test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Location> locations = GeoLocationUtils.queryGoogle("Via Cifariello 17, Molfetta", "it");
		return locations.get(0);
	}
	
	@RequestMapping(value = { "/test" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Location test(@RequestBody Location location, HttpServletResponse response) throws Exception {
		return location;
	}
	
}
