package com.neuralnoise.map.model.geo.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class PointDeserializer extends JsonDeserializer<Point> {

	private static final Logger log = LoggerFactory.getLogger(PointDeserializer.class);

	private GeometryFactory gf = new GeometryFactory();

	@Override
	public Point deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		ObjectCodec oc = jp.getCodec();
		JsonNode root = oc.readTree(jp);
		return parseGeometry(root);
	}

	private Point parseGeometry(JsonNode root) {
		String typeName = root.get("type").asText();
		if (typeName.equals("Point")) {
			return gf.createPoint(parseCoordinate((ArrayNode) root.get("coordinates")));
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private Coordinate parseCoordinate(JsonNode array) {
		return new Coordinate(array.get(0).asDouble(), array.get(1).asDouble());
	}
	
}
