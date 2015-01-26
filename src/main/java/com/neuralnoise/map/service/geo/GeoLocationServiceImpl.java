package com.neuralnoise.map.service.geo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neuralnoise.geo.GeoLocationUtils;
import com.neuralnoise.map.model.geo.Location;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

	private static final Logger log = LoggerFactory.getLogger(GeoLocationServiceImpl.class);

	public static final String LANGUAGE_DEFAULT = "en";

	public GeoLocationServiceImpl() {
	}

	@Override
	public List<Location> lookup(String address, ServiceType serviceType) throws IOException, InterruptedException {
		List<Location> locations = null;
		switch (serviceType) {
		case GOOGLE: {
			locations = GeoLocationUtils.queryGoogle(address, LANGUAGE_DEFAULT);
		}
			break;
		case NOMINATIM: {
			locations = GeoLocationUtils.queryNominatim(address, LANGUAGE_DEFAULT);
		}
			break;
		default: {
			throw new IllegalArgumentException("Unsupported service type: " + serviceType);
		}
		}
		return locations;
	}

}
