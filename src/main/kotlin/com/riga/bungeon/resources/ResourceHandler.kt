package com.riga.bungeon.resources

import com.riga.bungeon.exceptions.ResourceNotFoundException
import com.riga.bungeon.exceptions.ResourceParsingException
import com.riga.bungeon.resources.json.JsonAdapterFactory
import org.hexworks.zircon.api.application.AppConfig

import throws

object ResourceHandler {

	private fun getTextResource(path: String) = javaClass.getResource(path)
		.throws { ResourceNotFoundException("Resource not found!") }

	fun loadAppConfig() = getTextResource("/app.config.json").run {
		return@run JsonAdapterFactory.newAppConfigJsonAdapter()
			.fromJson(readText())
			.throws { ResourceParsingException("Resource could not be parsed!") }
	}
}