package com.riga.bungeon.resources.json.models

import org.hexworks.zircon.api.color.TileColor

data class TileColorJson(
	val red: Int,
	val green: Int,
	val blue: Int,
	val alpha: Int
) {
	fun serialize() = TileColor.create(red, green, blue, alpha)
}