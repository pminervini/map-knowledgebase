package com.neuralnoise.map.web.geo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.geo.GeoLocationService;
import com.neuralnoise.map.service.geo.GeoLocationService.ServiceType;

@Controller
@RequestMapping("/geoLocation")
public class GeoLocationController {

	private static final Logger log = LoggerFactory.getLogger(GeoLocationController.class);

	@Autowired
	private GeoLocationService geoLocationService;

	@RequestMapping(value = "/nominatim", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Location> nominatim(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final String address = request.getParameter("address");
		log.info("Locating {} ..", address);
		List<Location> locations = geoLocationService.lookup(address, ServiceType.NOMINATIM);
		response.setStatus(HttpStatus.OK.value());
		return locations;
	}

	@RequestMapping(value = "/google", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Location> google(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final String address = request.getParameter("address");
		log.info("Locating {} ..", address);
		List<Location> locations = geoLocationService.lookup(address, ServiceType.GOOGLE);
		response.setStatus(HttpStatus.OK.value());
		return locations;
	}

}
