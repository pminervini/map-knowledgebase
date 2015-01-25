package com.neuralnoise.map.model.geo;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@Embeddable
public class Location implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(Location.class);

	private static final long serialVersionUID = 8809624185680983201L;

	@Column(name = "address", length = 1024)
	@NotEmpty
	protected String address;

	@Column(name = "point")
	@Type(type = "org.hibernate.spatial.GeometryType")
	protected Point point;
	
	private static Geometry toGeometry(String wktPoint) {
		WKTReader fromText = new WKTReader();
		Geometry geom = null;
		try {
			geom = fromText.read(wktPoint);
		} catch (ParseException e) {
			throw new RuntimeException("Not a WKT string:" + wktPoint);
		}
		return geom;
	}

	public Location() { }

	public Location(Double latitude, Double longitude, String address) {
		this();
		this.setAddress(address);
		this.setPoint(latitude, longitude);
	}

	public Location(Point location, String address) {
		this();
		this.setPoint(location);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setPoint(Double latitude, Double longitude) {
		this.point = (Point) toGeometry("POINT(" + longitude + " " + latitude + ")");
	}

	@JsonIgnore
	public Map<String, String> getProperties() {
		Map<String, String> properties = Maps.newHashMap();
		if (address != null) {
			properties.put("address", address);
		}
		return properties;
	}
	
}
