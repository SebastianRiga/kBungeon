package com.riga.bungeon.system.json.models

import org.hexworks.zircon.api.data.Size

data class SizeJson(
	val width: Int,
	val height: Int
) {
	fun serialize() = Size.create(width, height)
}
