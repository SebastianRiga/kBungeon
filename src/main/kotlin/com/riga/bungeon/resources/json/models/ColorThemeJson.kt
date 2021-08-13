package com.riga.bungeon.resources.json.models

import org.hexworks.zircon.api.color.TileColor
import org.hexworks.zircon.api.component.ColorTheme

data class ColorThemeJson(
	val primaryForegroundColor: TileColorJson,
	val secondaryForegroundColor: TileColorJson,
	val primaryBackgroundColor: TileColorJson,
	val secondaryBackgroundColor: TileColorJson,
	val accentColor: TileColorJson
) {
	fun serialize(): ColorTheme = object : ColorTheme {
		override val accentColor: TileColor
			get() = this@ColorThemeJson.accentColor.serialize()
		override val primaryBackgroundColor: TileColor
			get() = this@ColorThemeJson.primaryBackgroundColor.serialize()
		override val primaryForegroundColor: TileColor
			get() = this@ColorThemeJson.primaryForegroundColor.serialize()
		override val secondaryBackgroundColor: TileColor
			get() = this@ColorThemeJson.secondaryBackgroundColor.serialize()
		override val secondaryForegroundColor: TileColor
			get() = this@ColorThemeJson.secondaryForegroundColor.serialize()
	}
}