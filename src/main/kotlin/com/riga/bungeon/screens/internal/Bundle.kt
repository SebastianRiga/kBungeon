package com.riga.bungeon.screens.internal

import com.riga.bungeon.system.json.JsonAdapterFactory

@Suppress("UNCHECKED_CAST")
class Bundle {
	private val data: MutableMap<String, String> = mutableMapOf()

	fun put(key: String, value: String): String? = data.put(key, value)

	fun put(key: String, value: Int): String? = data.put(key, value.toString())

	fun put(key: String, value: Long): String? = data.put(key, value.toString())

	fun put(key: String, value: Float): String? = data.put(key, value.toString())

	fun put(key: String, value: Double): String? = data.put(key, value.toString())

	fun put(key: String, value: Boolean): String? = data.put(key, value.toString())

	fun <T> putSerializable(key: String, value: T, clazz: Class<T>): String = JsonAdapterFactory
		.newJsonAdapter(clazz)
		.toJson(value).also {
			data[key] = it
		}

	fun <T : Any> get(key: String, default: T): T = data[key]?.let {
		return@let when (default::class) {
			String::class -> it as T
			Int::class -> it.toIntOrNull() as T
			Long::class -> it.toLongOrNull() as T
			Float::class -> it.toFloatOrNull() as T
			Double::class -> it.toDoubleOrNull() as T
			Boolean::class -> it.toBooleanStrictOrNull() as T
			else -> default
		}
	} ?: default
	
	fun <T: Any> get(key: String, clazz: Class<T>): T? = data[key]?.let {
		return@let when (clazz::class.java) {
			String::class -> it as T
			Int::class -> it.toIntOrNull() as T
			Long::class -> it.toLongOrNull() as T
			Float::class -> it.toFloatOrNull() as T
			Double::class -> it.toDoubleOrNull() as T
			Boolean::class -> it.toBooleanStrictOrNull() as T
			else -> null
		}
	}
	
	fun <T> getSerializable(key: String, default: T, clazz: Class<T>): T = JsonAdapterFactory
		.newJsonAdapter(clazz).run { 
			data[key]?.let { 
				return@run this.fromJson(it)
			} ?: default
		} ?: default
}