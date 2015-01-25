package com.neuralnoise.map.service.geo;

import java.util.List;

import com.neuralnoise.map.model.geo.Location;

public interface GeoLocationService {

	public enum ServiceType {
		GOOGLE, NOMINATIM
	};

	public List<Location> lookup(String address, ServiceType serviceType) throws Exception;

}
