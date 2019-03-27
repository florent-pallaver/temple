package diet.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class MapBodyReader implements MessageBodyReader<Map<String, Integer>>{

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Map.class.isAssignableFrom(type);
	}

	@Override
	public Map<String, Integer> readFrom(Class<Map<String, Integer>> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String,String> httpHeaders, InputStream entityStream) 
			throws IOException, WebApplicationException {
		final Map<String, Integer> map = new HashMap<>();
		try {
			final JsonObject object = Json.createReader(entityStream).readObject();
			object.forEach((key, value) -> map.put(key, ((JsonNumber)value).intValueExact()));
		} catch (RuntimeException e) {
			throw new BadRequestException("Given JSON cannot be converted to Map<String, Integer>", e);
		}
		return map;
	}

}