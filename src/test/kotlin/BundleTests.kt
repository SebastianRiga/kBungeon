import com.riga.bungeon.screens.internal.Bundle
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BundleTests {
	
	@Test
	fun handleInt() {
		val bundle = Bundle()
		
		bundle.put("test", 1337)
		
		val existingValue = bundle.get("test", 0)
		val defaultValue = bundle.get("test2", 7331)
		
		Assertions.assertThat(existingValue).isEqualTo(1337)
		Assertions.assertThat(defaultValue).isEqualTo(7331)
	}
}