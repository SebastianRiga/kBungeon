package com.riga.bungeon.system.json

import com.riga.bungeon.system.json.adapter.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.hexworks.zircon.api.application.AppConfig

object JsonAdapterFactory {

	private fun baseJsonAdapterBuilder() = Moshi.Builder()
		.addLast(KotlinJsonAdapterFactory())

	fun <T> newJsonAdapter(clazz: Class<T>): JsonAdapter<T> = baseJsonAdapterBuilder()
		.build()
		.adapter(clazz)

	fun newAppConfigJsonAdapter(): JsonAdapter<AppConfig> = baseJsonAdapterBuilder()
		.add(TileColorJsonAdapter())
		.add(TilesetResourceJsonAdapter())
		.add(ColorThemeJsonAdapter())
		.add(SizeJsonAdapter())
		.add(DebugConfigJsonAdapter())
		.build()
		.adapter(AppConfig::class.java)
}