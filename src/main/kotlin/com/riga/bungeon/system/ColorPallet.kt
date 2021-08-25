package com.riga.bungeon.system

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.color.TileColor
import org.hexworks.zircon.api.component.ColorTheme

@Suppress("MemberVisibilityCanBePrivate")
object ColorPallet {
	
	val PRIMARY_FOREGROUND_COLOR = TileColor.fromString("#33ff00")
	val SECONDARY_FOREGROUND_COLOR = TileColor.fromString("#00ff66")
	val PRIMARY_BACKGROUND_COLOR = TileColor.fromString("#171717")
	val SECONDARY_BACKGROUND_COLOR = TileColor.fromString("#000000")
	val COLOR_ACCENT = TileColor.fromString("#FFB000")
	
	fun getColorTheme(): ColorTheme = ColorThemes.newBuilder()
		.withPrimaryForegroundColor(PRIMARY_FOREGROUND_COLOR)
		.withSecondaryForegroundColor(SECONDARY_FOREGROUND_COLOR)
		.withPrimaryBackgroundColor(PRIMARY_BACKGROUND_COLOR)
		.withSecondaryBackgroundColor(SECONDARY_BACKGROUND_COLOR)
		.withAccentColor(COLOR_ACCENT)
		.build()
}