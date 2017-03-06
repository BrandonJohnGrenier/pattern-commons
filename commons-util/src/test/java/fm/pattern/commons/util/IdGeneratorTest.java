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

import static org.apache.commons.lang3.StringUtils.isAlpha;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import fm.pattern.commons.util.IdGenerator;

public class IdGeneratorTest {

	@Test
	public void generatedIdsShouldAlwaysStartWithACharacter() {
		for (int i = 0; i < 1000; i++) {
			assertThat(isAlpha(String.valueOf(IdGenerator.generateId().charAt(0)))).isTrue();
		}
	}

	@Test
	public void shouldBeAbleToGenerateAnIdWithAPrefix() {
		String id = IdGenerator.generateId("acc");
		assertThat(id).startsWith("acc_");
	}

	@Test
	public void shouldNotGenerateAnIdWithAPrefixIfThePrefixIsEmpty() {
		assertThat(IdGenerator.generateId("").length()).isEqualTo(25);
		assertThat(IdGenerator.generateId((String) null).length()).isEqualTo(25);
	}

	@Test
	public void shouldCreateAnIdWith25CharactersByDefaultIfTheLengthIsNotSpecified() {
		String id = IdGenerator.generateId();
		assertThat(id.length()).isEqualTo(25);
	}

	@Test
	public void shouldBeAbleToGenerateAnIdWithTheSpecifiedLength() {
		String id = IdGenerator.generateId(15);
		assertThat(id.length()).isEqualTo(15);
	}

	@Test
	public void shouldBeAbleToGenerateAnIdWithTheSpecifiedLengthAndTheSpecifiedPrefix() {
		String id = IdGenerator.generateId("cus", 20);
		assertThat(id.startsWith("cus_"));
		assertThat(id.length()).isEqualTo(24);
	}
	
	@Test
	public void shouldGenerateAnIdWithTheSpecifiedLengthIfThePrefixIsNullOrEmpty() {
		assertThat(IdGenerator.generateId(null, 20).length()).isEqualTo(20);
		assertThat(IdGenerator.generateId("", 20).length()).isEqualTo(20);
		assertThat(IdGenerator.generateId("  ", 20).length()).isEqualTo(20);
	}
	
}
