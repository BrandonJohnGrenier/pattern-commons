/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
