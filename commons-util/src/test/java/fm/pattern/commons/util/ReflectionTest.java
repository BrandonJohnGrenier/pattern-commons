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

public class ReflectionTest {

    @Test
    public void shouldBeAbleToReturnAPropertyValueFromAnObject() {
        SimpleModel model = new SimpleModel("value", "value", 200);

        assertThat(Reflection.get(model, "first")).isEqualTo("value");
        assertThat(Reflection.get(model, "last")).isEqualTo("value");
        assertThat(Reflection.get(model, "total")).isEqualTo(200);
    }

    @Test
    public void shouldNotBeAbleToReturnAPropertyValueFromAnObjectWhenThePropertyDoesNotExist() {
        SimpleModel model = new SimpleModel("value", "value", 200);
        assertThat(Reflection.get(model, "invalid")).isNull();
    }

    @Test
    public void shouldBeAbleToGetPropertyValuesFromASuperclass() {
        SimpleModel model = new SimpleModel("first", "last", 200);
        model.setKey("mykey");
        model.setValue("myvalue");

        assertThat(Reflection.get(model, "key")).isEqualTo("mykey");
        assertThat(Reflection.get(model, "value")).isEqualTo("myvalue");
    }

    @Test
    public void shouldBeAbleToGetPropertyValuesFromAComposedObject() {
        ComposedModel composed = new ComposedModel();
        composed.setHours(10);
        composed.setMinutes(40);
        composed.setKey("some key");
        composed.setValue("some value");

        SimpleModel model = new SimpleModel("first", "last", 200);
        model.setModel(composed);

        assertThat(Reflection.get(model, "model.hours")).isEqualTo(10);
        assertThat(Reflection.get(model, "model.minutes")).isEqualTo(40);
        assertThat(Reflection.get(model, "model.key")).isEqualTo("some key");
        assertThat(Reflection.get(model, "model.value")).isEqualTo("some value");
    }

    @Test
    public void shouldBeAbleToExplicityRequestTheReturnTypeWhenGettingAValue() {
        ComposedModel composed = new ComposedModel();
        composed.setHours(10);
        composed.setMinutes(40);
        composed.setKey("some key");
        composed.setValue("some value");

        SimpleModel model = new SimpleModel("first", "last", 200);
        model.setModel(composed);

        ComposedModel reflected = Reflection.get(model, "model", ComposedModel.class);
        assertThat(reflected.getHours()).isEqualTo(10);
        assertThat(reflected.getMinutes()).isEqualTo(40);
        assertThat(reflected.getKey()).isEqualTo("some key");
        assertThat(reflected.getValue()).isEqualTo("some value");
    }

    @Test
    public void shouldReturnNullBWhenThePropertyToGetIsNotPresent() {
        ComposedModel composed = new ComposedModel();
        composed.setHours(10);
        composed.setMinutes(40);
        composed.setKey("some key");
        composed.setValue("some value");

        SimpleModel model = new SimpleModel("first", "last", 200);
        model.setModel(composed);

        ComposedModel reflected = Reflection.get(model, "invalidprop", ComposedModel.class);
        assertThat(reflected).isNull();
    }
    
    @Test
    public void shouldBeAbleToSetThePropertyValueOfTheTargetObject() {
        SimpleModel model = new SimpleModel("value", "value", 200);

        Reflection.set(model, "first", "another value");
        Reflection.set(model, "last", "another value");
        Reflection.set(model, "total", 500);

        assertThat(model.getFirst()).isEqualTo("another value");
        assertThat(model.getLast()).isEqualTo("another value");
        assertThat(model.getTotal()).isEqualTo(500);
    }

    @Test
    public void shouldBeAbleToSetThePropertyValueOnASuperclassOfTheTargetObject() {
        SimpleModel model = new SimpleModel("value", "value", 200);

        Reflection.set(model, "key", "some key");
        Reflection.set(model, "value", "some value");

        assertThat(model.getKey()).isEqualTo("some key");
        assertThat(model.getValue()).isEqualTo("some value");
    }

    @Test
    public void shouldBeAbleToSetThePropertyValueOnAComposedObject() {
        ComposedModel composed = new ComposedModel();

        SimpleModel model = new SimpleModel("value", "value", 200);
        model.setModel(composed);

        Reflection.set(model, "model.minutes", 20);
        Reflection.set(model, "model.hours", 20);
        Reflection.set(model, "model.key", "some key");
        Reflection.set(model, "model.value", "some value");

        assertThat(model.getModel().getHours()).isEqualTo(20);
        assertThat(model.getModel().getMinutes()).isEqualTo(20);
        assertThat(model.getModel().getKey()).isEqualTo("some key");
        assertThat(model.getModel().getValue()).isEqualTo("some value");
    }

    @Test
    public void shouldNotBeAbleToSetThePropertyValueOnAComposedObjectWhenThePropertyNameIsInvalid() {
        ComposedModel composed = new ComposedModel();
        SimpleModel model = new SimpleModel("value", "value", 200);
        model.setModel(composed);

        Reflection.set(model, "mdel.minutes", 20);
        Reflection.set(model, "moel.ours", 20);

        assertThat(model.getModel().getHours()).isEqualTo(null);
        assertThat(model.getModel().getMinutes()).isEqualTo(null);
    }

    @Test
    public void shouldNotBeAbleToSetThePropertyValueOnAnObjectWhenTheObjectIsNull() {
        SimpleModel model = new SimpleModel("value", "value", 200);

        Reflection.set(null, "key", "some key");
        Reflection.set(model, null, "some value");

        assertThat(model.getKey()).isEqualTo(null);
        assertThat(model.getValue()).isEqualTo(null);
    }

    @Test
    public void theClassShouldBeAWellDefinedUtilityClass() {
        PatternAssertions.assertClass(Reflection.class).isAWellDefinedUtilityClass();
    }

}
