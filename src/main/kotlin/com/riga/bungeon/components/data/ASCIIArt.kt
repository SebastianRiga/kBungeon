package com.riga.bungeon.components.data

import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.data.Size

data class ASCIIArt(val content: List<String>) {

	val size: Size

	init {
		val longestStringInList = content.maxByOrNull { it.length }?.length ?: 1

		size = Size.create(longestStringInList, content.size)
	}

	fun newComponentBuilder() = Components.textBox(size.width).also {
		content.forEach { line -> it.addHeader(line) }
	}
}
