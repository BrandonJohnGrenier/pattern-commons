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
		assertThat(IdGenerator.generateId("").length()).isEqualTo(16);
		assertThat(IdGenerator.generateId((String) null).length()).isEqualTo(16);
	}

	@Test
	public void shouldBeAbleToCreateAnIdWith16CharactersInLengthIfTheLengthIsNotSpecified() {
		String id = IdGenerator.generateId();
		assertThat(id.length()).isEqualTo(16);
	}

	@Test
	public void shouldBeAbleToGenerateAnIdWithTheSpecifiedLength() {
		String id = IdGenerator.generateId(15);
		assertThat(id.length()).isEqualTo(15);
	}

}
