package com.riga.bungeon.screens

import com.riga.bungeon.core.data.GameConfig
import com.riga.bungeon.screens.internal.BaseScreen
import com.riga.bungeon.screens.internal.Bundle
import com.riga.bungeon.system.SoundSystem
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.grid.TileGrid

class NewGameScreen(tileGrid: TileGrid, gameConfig: GameConfig, bundle: Bundle? = null) :
	BaseScreen(tileGrid, gameConfig, bundle) {

	init {

		val exitButton = Components.button()
			.withText("Start")
			.withAlignmentWithin(screen, ComponentAlignment.CENTER)
			.build()
		exitButton.onClick(::onBackButtonClick)

		screen.addComponents(exitButton)
	}

	override fun onDock() {
		super.onDock()
		SoundSystem.playBackgroundMusic("new_game")
	}

	private fun onBackButtonClick() = navigateTo(PlayScreen::class)
}