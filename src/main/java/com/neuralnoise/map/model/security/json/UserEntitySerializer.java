package com.neuralnoise.map.model.security.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.neuralnoise.map.model.security.UserEntity;

public class UserEntitySerializer extends JsonSerializer<UserEntity> {

	@Override
	public void serialize(UserEntity value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeStringField("name", value.getName());
		jgen.writeBooleanField("admin", value.isAdmin());
		jgen.writeEndObject();

	}

}
