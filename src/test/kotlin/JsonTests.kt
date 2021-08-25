import com.riga.bungeon.system.json.JsonAdapterFactory
import helpers.JsonTestHelper
import org.hexworks.zircon.api.application.AppConfig
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class JsonTests {

	@Test
	fun appConfigSerialization() {
		assertDoesNotThrow("JsonTests - Checking serialization of AppConfig with default values:") {
			val adapter = JsonAdapterFactory.newAppConfigJsonAdapter()
			val expected = AppConfig.defaultConfiguration()
			val actual = adapter.fromJson(JsonTestHelper.appConfigDefaultJson)

			JsonTestHelper.compareAppConfigs(actual, expected)
		}
	}

	@Test
	fun appConfigDeserialization() {
		assertDoesNotThrow("JsonTests - Checking deserialization of AppConfig with default values:") {
			val adapter = JsonAdapterFactory.newAppConfigJsonAdapter()
			val expected = AppConfig.defaultConfiguration()
			val json = adapter.toJson(expected)
			val actual = adapter.fromJson(json)

			JsonTestHelper.compareAppConfigs(actual, expected)
		}
	}

}