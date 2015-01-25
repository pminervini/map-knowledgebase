package com.neuralnoise.map.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.neuralnoise.map.model.geo.json.GeometryDeserializer;
import com.neuralnoise.map.model.geo.json.GeometrySerializer;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.model.security.json.UserEntitySerializer;
import com.vividsolutions.jts.geom.Geometry;

public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -2314203051178022125L;

	public CustomObjectMapper() {
		SimpleModule module = new SimpleModule("MAPJSONModule");

		module.addSerializer(Geometry.class, new GeometrySerializer());
		module.addSerializer(UserEntity.class, new UserEntitySerializer());

		module.addDeserializer(Geometry.class, new GeometryDeserializer());

		this.registerModule(module);
		this.registerModule(new JodaModule());
	}

}
