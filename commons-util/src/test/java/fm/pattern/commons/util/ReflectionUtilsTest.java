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

import fm.pattern.commons.util.model.ComposedModel;
import fm.pattern.commons.util.model.SimpleModel;

public class ReflectionUtilsTest {

	@Test
	public void shouldBeAbleToReturnAPropertyValueFromAnObject() {
		SimpleModel model = new SimpleModel("value", "value", 200);

		assertThat(ReflectionUtils.getValue(model, "first")).isEqualTo("value");
		assertThat(ReflectionUtils.getValue(model, "last")).isEqualTo("value");
		assertThat(ReflectionUtils.getValue(model, "total")).isEqualTo(200);
	}

	@Test
	public void shouldNotBeAbleToReturnAPropertyValueFromAnObjectWhenThePropertyDoesNotExist() {
		SimpleModel model = new SimpleModel("value", "value", 200);
		assertThat(ReflectionUtils.getValue(model, "invalid")).isNull();
	}

	@Test
	public void shouldBeAbleToSetThePropertyValueOfTheTargetObject() {
		SimpleModel model = new SimpleModel("value", "value", 200);

		ReflectionUtils.setValue(model, "first", "another value");
		ReflectionUtils.setValue(model, "last", "another value");
		ReflectionUtils.setValue(model, "total", 500);

		assertThat(model.getFirst()).isEqualTo("another value");
		assertThat(model.getLast()).isEqualTo("another value");
		assertThat(model.getTotal()).isEqualTo(500);
	}

	@Test
	public void shouldBeAbleToSetThePropertyValueOnASuperclassOfTheTargetObject() {
		SimpleModel model = new SimpleModel("value", "value", 200);

		ReflectionUtils.setValue(model, "key", "some key", 1);
		ReflectionUtils.setValue(model, "value", "some value", 1);

		assertThat(model.getKey()).isEqualTo("some key");
		assertThat(model.getValue()).isEqualTo("some value");
	}

	@Test
	public void shouldNotBeAbleToSetThePropertyValueOnASuperclassOfTheTargetObjectWhenTheAncestorIsInvalid() {
		SimpleModel model = new SimpleModel("value", "value", 200);

		ReflectionUtils.setValue(model, "key", "some key", 6);
		ReflectionUtils.setValue(model, "value", "some value", 6);

		assertThat(model.getKey()).isNull();
		assertThat(model.getValue()).isNull();
	}

	@Test
	public void shouldBeAbleToSetThePropertyValueOnAComposedObject() {
		ComposedModel composed = new ComposedModel();
		SimpleModel model = new SimpleModel("value", "value", 200);
		model.setModel(composed);

		ReflectionUtils.setValue(model, "model.minutes", 20);
		ReflectionUtils.setValue(model, "model.hours", 20);

		assertThat(model.getModel().getHours()).isEqualTo(20);
		assertThat(model.getModel().getMinutes()).isEqualTo(20);
	}

	@Test
	public void shouldNotBeAbleToSetThePropertyValueOnAComposedObjectWhenThePropertyNameIsInvalid() {
		ComposedModel composed = new ComposedModel();
		SimpleModel model = new SimpleModel("value", "value", 200);
		model.setModel(composed);

		ReflectionUtils.setValue(model, "mdel.minutes", 20);
		ReflectionUtils.setValue(model, "moel.ours", 20);

		assertThat(model.getModel().getHours()).isEqualTo(null);
		assertThat(model.getModel().getMinutes()).isEqualTo(null);
	}

	@Test
	public void shouldNotBeAbleToSetThePropertyValueOnAnObjectWhenTheObjectIsNull() {
		SimpleModel model = new SimpleModel("value", "value", 200);

		ReflectionUtils.setValue(null, "key", "some key", 1);
		ReflectionUtils.setValue(model, null, "some value", 1);

		assertThat(model.getKey()).isEqualTo(null);
		assertThat(model.getValue()).isEqualTo(null);
	}

	@Test
	public void shouldNotBeAbleToSetThePropertyValueOnAComposedObjectWithAnInvalidAncestor() {
		ComposedModel composed = new ComposedModel();
		SimpleModel model = new SimpleModel("value", "value", 200);
		model.setModel(composed);

		ReflectionUtils.setValue(model, "model.minutes", 20, 5);
		ReflectionUtils.setValue(model, "model.hours", 20, 5);

		assertThat(model.getModel().getHours()).isNull();
		assertThat(model.getModel().getMinutes()).isNull();
	}

}
