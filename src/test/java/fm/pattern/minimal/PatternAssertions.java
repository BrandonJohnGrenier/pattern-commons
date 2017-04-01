package fm.pattern.minimal;

import org.assertj.core.api.Assertions;

public class PatternAssertions extends Assertions {

	public static UtilityClassAssertions assertClass(Class<?> clazz) {
		return new UtilityClassAssertions(clazz);
	}

}
