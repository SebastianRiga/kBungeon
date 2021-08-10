import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Test {

	@Test
	fun dummy() {
		Assertions.assertThat("foo").isNotEqualTo("bar")
	}
}