package com.riga.bungeon.core.data

import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.resource.TilesetResource

data class GameConfig(
	val title: String,
	val windowWidth: Int,
	val windowHeight: Int,
	val dungeonDepth: Int,
	val tileset: TilesetResource,
	val colorTheme: ColorTheme
) {
	val worldSize = Size3D.create(windowWidth, windowHeight, dungeonDepth)

	fun toAppConfig(): AppConfig = AppConfig.newBuilder()
		.withTitle(title)
		.withDefaultTileset(tileset)
		.withSize(windowWidth, windowHeight)
		.build()
}