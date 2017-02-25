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

public class ReflectionUtilsTest {

	@Test
	public void shouldBeAbleToExtractAStringValueFromAnObject() {
		SimpleModel model = new SimpleModel("value", "value", 200);

		assertThat(ReflectionUtils.getString(model, "first")).isEqualTo("value");
		assertThat(ReflectionUtils.getString(model, "last")).isEqualTo("value");
		assertThat(ReflectionUtils.getString(model, "total")).isEqualTo(null);
	}

	@Test
	public void shouldBeAbleToExtractAnObjectFromAnObject() {
		SimpleModel model = new SimpleModel("value", "value", 200);

		assertThat(ReflectionUtils.getObject(model, "first")).isEqualTo("value");
		assertThat(ReflectionUtils.getObject(model, "last")).isEqualTo("value");
		assertThat(ReflectionUtils.getObject(model, "total")).isEqualTo(200);
	}

	@Test
	public void shouldBeAbleToSetAnObjectValue() {
		SimpleModel model = new SimpleModel("value", "value", 200);

		ReflectionUtils.setValue(model, "first", "another value");
		ReflectionUtils.setValue(model, "last", "another value");
		ReflectionUtils.setValue(model, "total", 500);

		assertThat(model.getFirst()).isEqualTo("another value");
		assertThat(model.getLast()).isEqualTo("another value");
		assertThat(model.getTotal()).isEqualTo(500);
	}

}
