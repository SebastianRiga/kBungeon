package com.riga.bungeon.system

import com.riga.bungeon.components.data.ASCIIArt
import com.riga.bungeon.exceptions.ResourceNotFoundException
import com.riga.bungeon.exceptions.ResourceParsingException
import com.riga.bungeon.meta.extensions.throws
import com.riga.bungeon.system.json.JsonAdapterFactory
import org.hexworks.zircon.api.application.AppConfig
import java.io.InputStream
import java.net.URL
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

object ResourceLoader {

	private const val ASCII_RESOURCE_PREFIX = "/ascii/"
	private const val ASCII_RESOURCE_SUFFIX = ".ascii"

	private const val AUDIO_RESOURCE_PREFIX = "/audio/"
	private const val AUDIO_RESOURCE_SUFFIX = ".wav"

	private fun getTextResource(path: String): URL = javaClass.getResource(path)
		.throws { ResourceNotFoundException("The resource at path $path could not be found!") }

	private fun getStreamResource(path: String): InputStream = javaClass.getResourceAsStream(path)
		.throws { ResourceNotFoundException("The resource at path $path could not be found!") }

	fun loadAppConfig(): AppConfig = getTextResource("/app.config.json").run {
		return@run JsonAdapterFactory.newAppConfigJsonAdapter()
			.fromJson(readText())
			.throws { ResourceParsingException("Resource could not be parsed!") }
	}

	fun loadASCIIArt(name: String): ASCIIArt = "$ASCII_RESOURCE_PREFIX$name$ASCII_RESOURCE_SUFFIX".let {
		getStreamResource(it)
			.bufferedReader()
			.lines()
			.toList()
			.run {
				return@run ASCIIArt(this)
			}
	}

	fun loadAudio(name: String): AudioInputStream = "$AUDIO_RESOURCE_PREFIX$name$AUDIO_RESOURCE_SUFFIX".let {
		getStreamResource(it).run {
			AudioSystem.getAudioInputStream(this)
		}
	}
}