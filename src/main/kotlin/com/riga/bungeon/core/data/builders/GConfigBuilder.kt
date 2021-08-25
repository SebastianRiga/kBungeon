package com.riga.bungeon.core.data.builders

import com.riga.bungeon.core.data.GameConfig
import com.riga.bungeon.system.ColorPallet
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.resource.TilesetResource

class GConfigBuilder private constructor() {

	private var title = "Bionic Rouge"

	private var windowWidth = 80
	private var windowHeight = 60

	private var dungeonDeath = 2

	private var tileset = CP437TilesetResources.bisasam20x20()
	private var colorTheme = ColorPallet.getColorTheme()

	companion object {
		fun newDefault(): GameConfig = GConfigBuilder().build()

		fun newBuilder(init: GConfigBuilder.() -> Unit): GameConfig = GConfigBuilder().apply(init).build()
	}

	fun title(config: () -> String) = config().also { title = it }

	fun windowWidth(config: () -> Int) = config().also { windowWidth = it }

	fun windowHeight(config: () -> Int) = config().also { windowHeight = it }

	fun dungeonDepth(config: () -> Int) = config().also { dungeonDeath = it }

	fun tileset(config: () -> TilesetResource) = config().also { tileset = it }

	fun colorTheme(config: () -> ColorTheme) = config().also { colorTheme = it }

	private fun build() = GameConfig(title, windowWidth, windowHeight, dungeonDeath, tileset, colorTheme)
}