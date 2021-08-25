import com.riga.bungeon.core.maps.BspBuilder
import com.riga.bungeon.meta.classes.BackingField
import org.assertj.core.api.Assertions
import org.hexworks.zircon.api.data.Size3D
import org.junit.jupiter.api.Test

class BaseTests {

	private var Int.prodNumber: Float by BackingField { it.toFloat() }
	
	@Test
	fun scribble() {
		val num = 2.also {
			it.prodNumber = 20f
		}
		
		Assertions.assertThat(num.prodNumber).isEqualTo(20f)
	}
}