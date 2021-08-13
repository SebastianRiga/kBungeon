package com.riga.bungeon.resources.json.models

import org.hexworks.zircon.api.data.Size

data class SizeJson(
	val width: Int,
	val height: Int
) {
	fun serialize() = Size.create(width, height)
}
