package com.riga.bungeon.meta.extensions

import mu.KotlinLogging
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random

inline fun <T> T.doToo(lambda: T.() -> Unit) {
	lambda()
}

inline fun <T, R : Exception> T?.throws(exception: () -> R): T =
	this?.let {
		return@let it
	} ?: run {
		throw exception()
	}

inline fun <T> guard(unsafeBlock: () -> T, handler: (e: Exception) -> Unit, block: (save: T) -> Unit) {
	return try {
		val res = unsafeBlock()
		block(res)
	} catch (e: Exception) {
		handler(e)
	}
}

inline fun schedule(millis: Long, name: String = "", crossinline block: () -> Unit) =
	Timer(name, false).schedule(millis) {
		block()
	}

fun <T> Boolean.branch(truthyBlock: () -> T, falsyBlock: () -> T) = if (this) truthyBlock() else falsyBlock()

fun <T, R> Map<T, R>.runIfElementExists(position: T, lambda: (R) -> Unit) = this[position]?.let(lambda)

fun <T> MutableList<T>.addAll(vararg elements: T) = this.addAll(elements)

fun rng() = Random.nextInt()

fun rng(start: Int, end: Int): Int = (start <= end).branch({
	start
}, {
	Random.nextInt(start, end)
})

val GlobalLogger = KotlinLogging.logger {}
