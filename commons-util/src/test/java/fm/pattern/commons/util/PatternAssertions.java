package fm.pattern.commons.util;

import org.assertj.core.api.Assertions;

public class PatternAssertions extends Assertions {

	public static ResultAssertions assertClass(Class<?> clazz) {
		return new ResultAssertions(clazz);
	}

}
