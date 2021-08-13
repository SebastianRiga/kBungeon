import mu.KotlinLogging

inline fun <T, R: Exception> T?.throws(exception: () -> R): T =
	this?.let {
		return@let it
	} ?: run {
		throw exception()
	}

inline fun guard(handler: (e: Exception) -> Unit, block: () -> Unit) {
	try {
		block()
	} catch (e: Exception) {
		handler(e)
	}
}

inline fun <T> guard(unsafeBlock: () -> T, handler: (e: Exception) -> Unit, block: (save: T) -> Unit) {
	return try {
		val res = unsafeBlock()
		block(res)
	} catch (e: Exception) {
		handler(e)
	}
}

inline fun <T> T?.default(producer: () -> T): T =
	this?.let {
		return@let it
	} ?: run {
		producer()
	}

val GlobalLogger = KotlinLogging.logger {}
