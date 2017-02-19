package fm.pattern.commons.util;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import fm.pattern.commons.util.model.SimpleModel;

public class JSONTest {

	@Test
	public void shouldBeAbleToStringifyAnObject() {
		SimpleModel model = new SimpleModel();
		model.setFirst("first");
		model.setLast("last");
		model.setTotal(55);

		String string = JSON.stringify(model);
		assertThat(string).isEqualTo("{\"first\":\"first\",\"last\":\"last\",\"total\":55}");
	}

	@Test
	public void shouldBeAbleToObjectifyAString() {
		SimpleModel model = JSON.objectify("{\"first\":\"first\",\"last\":\"last\",\"total\":55}", SimpleModel.class);
		assertThat(model.getFirst()).isEqualTo("first");
		assertThat(model.getLast()).isEqualTo("last");
		assertThat(model.getTotal()).isEqualTo(55);
	}

}
