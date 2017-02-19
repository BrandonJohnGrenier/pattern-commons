package fm.pattern.commons.util;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public final class JSON {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}

	private JSON() {

	}

	public static <T> T objectify(String source, Class<T> type) {
		try {
			return objectMapper.readValue(source, type);
		}
		catch (Exception e) {
			throw new JsonParsingException(e);
		}
	}

	public static String stringify(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		}
		catch (Exception e) {
			throw new JsonParsingException(e);
		}
	}

	public static Map<String, Object> map(Object object) {
		try {
			return objectMapper.convertValue(object, Map.class);
		}
		catch (Exception e) {
			throw new JsonParsingException(e);
		}
	}

}
