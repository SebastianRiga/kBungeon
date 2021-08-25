package com.riga.bungeon.screens

import com.riga.bungeon.core.data.GameConfig
import com.riga.bungeon.core.data.builders.GConfigBuilder
import com.riga.bungeon.screens.internal.BaseScreen
import com.riga.bungeon.screens.internal.Bundle
import com.riga.bungeon.system.ResourceLoader
import com.riga.bungeon.system.SoundSystem
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.grid.TileGrid

class FatalErrorScreen(tileGrid: TileGrid, gameConfig: GameConfig, bundle: Bundle? = null) :
	BaseScreen(tileGrid, gameConfig, bundle) {

	constructor(bundle: Bundle? = null) : this(SwingApplications.startTileGrid(), GConfigBuilder.newDefault(), bundle)

	init {
		val topSpacer = Components.vSpacer(2)
			.withAlignmentWithin(screen, ComponentAlignment.TOP_CENTER)
			.build()

		val errorLogo = ResourceLoader.loadASCIIArt("error")
			.newComponentBuilder()
			.withAlignmentAround(topSpacer, ComponentAlignment.BOTTOM_CENTER)
			.build()

		val errorReason = bundle?.get("message", "") ?: ""

		val errorMessage = Components.header()
			.withText(errorReason)
			.withAlignmentWithin(screen, ComponentAlignment.CENTER)
			.build()

		val bottomSpacer = Components.vSpacer(2)
			.withAlignmentWithin(screen, ComponentAlignment.BOTTOM_CENTER)
			.build()

		val exitButton = Components.button()
			.withText("Return to OS")
			.withAlignmentAround(bottomSpacer, ComponentAlignment.TOP_CENTER)
			.build()
		exitButton.onClickDelay(400L) { exitGame() }

		screen.addComponents(topSpacer, errorLogo, errorMessage, bottomSpacer, exitButton)
	}

	override fun onDock() {
		super.onDock()
		SoundSystem.playSoundEffect("error2")
	}
}