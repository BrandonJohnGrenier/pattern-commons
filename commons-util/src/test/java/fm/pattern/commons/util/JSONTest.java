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

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import fm.pattern.commons.util.model.SimpleModel;

public class JSONTest {

	private static final String JSON_STRING = "{\"key\":null,\"value\":null,\"first\":\"first\",\"last\":\"last\",\"total\":55,\"model\":null}";

	@Test
	public void shouldBeAbleToStringifyAnObject() {
		SimpleModel model = new SimpleModel();
		model.setFirst("first");
		model.setLast("last");
		model.setTotal(55);

		String string = JSON.stringify(model);
		assertThat(string).isEqualTo(JSON_STRING);
	}

	@Test
	public void shouldBeAbleToObjectifyAString() {
		SimpleModel model = JSON.parse(JSON_STRING, SimpleModel.class);
		assertThat(model.getFirst()).isEqualTo("first");
		assertThat(model.getLast()).isEqualTo("last");
		assertThat(model.getTotal()).isEqualTo(55);
	}

	@Test(expected = JsonParsingException.class)
	public void shouldThrowAParsingExceptionWhenAJSONStringCannotBeParsed() {
		JSON.parse("no{}", SimpleModel.class);
	}

	@Test
	public void theClassShouldBeAWellDefinedUtilityClass() {
		PatternAssertions.assertClass(JSON.class).isAWellDefinedUtilityClass();
	}

}
