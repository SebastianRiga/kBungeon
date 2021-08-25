package com.riga.bungeon.screens

/**
 * TitleScreen.kt(c) 2021 Sebastian Riga <sebastian.riga.development@gmail.com>.
 *
 * File ID: $UUID
 *
 * Created on 16.08.2021, Mon, 16:40
 *
 * For specific licensing information and how to adhere to the requirements of the licence to use this code,
 * see LICENCE.md.
 *
 * DO NOT CHANGE OR REMOVE THIS FILE HEADER OR ANY FILES ASSOCIATED WITH COPYRIGHT OR LICENCES UNDER ANY CIRCUMSTANCES!
 */

// ################################################ Imports ################################################

import com.riga.bungeon.core.data.GameConfig
import com.riga.bungeon.screens.internal.BaseScreen
import com.riga.bungeon.screens.internal.Bundle
import com.riga.bungeon.system.ResourceLoader
import com.riga.bungeon.system.SoundSystem
import com.riga.bungeon.system.localization.GameTexts
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.AttachedComponent
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.component.VBox
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.grid.TileGrid

// ################################################ Implementation ################################################

class TitleScreen(tileGrid: TileGrid, gameConfig: GameConfig, bundle: Bundle? = null) :
	BaseScreen(tileGrid, gameConfig, bundle) {

	// #####################################################
	// # Properties
	// #####################################################

	private val startButtonComponent: AttachedComponent

	// #####################################################
	// # Init
	// #####################################################

	init {
		screen.apply {
			val logo = ResourceLoader.loadASCIIArt("logo")

			val logoTopSpacer = Components.vSpacer(2)
				.withAlignmentWithin(screen, ComponentAlignment.TOP_CENTER)
				.build()

			val logoCmp = logo.newComponentBuilder()
				.withAlignmentAround(logoTopSpacer, ComponentAlignment.BOTTOM_CENTER)
				.build()

			val startButton = Components.button()
				.withAlignmentWithin(screen, ComponentAlignment.CENTER)
				.withText(" C:\\> exec Bionic_Rouge.cmd")
				.build()
			startButton.onClick(::onStartButtonClick)

			val imprint = Components.textBox(GameTexts.imprint.length)
				.addHeader(GameTexts.imprint)
				.addNewLine()
				.withAlignmentWithin(screen, ComponentAlignment.BOTTOM_CENTER)
				.build()

			startButtonComponent = screen.addComponent(startButton)
			addComponents(logoTopSpacer, logoCmp, imprint)
		}
	}

	// #####################################################
	// # Lifecycle
	// #####################################################

	override fun onDock() {
		super.onDock()
		SoundSystem.playBackgroundMusic("title")
	}

	// #####################################################
	// # UI-Handling
	// #####################################################

	private fun createStartMenu(): VBox = Components.vbox()
		.withAlignmentWithin(screen, ComponentAlignment.CENTER)
		.withSize(54, 24)
		.withDecorations(box(BoxType.SINGLE, "Bionic_Rouge:\\> "))
		.build()
		.apply {

			val header = Components.textBox("Execute function...".length)
				.addNewLine()
				.addNewLine()
				.addHeader("Execute function...")
				.addNewLine()
				.build()

			val continueButton = Components.buttonSimple("--> Continue(lastSave); // Load newest save")
			continueButton.onClick(::onContinueButtonClick)

			val continueButtonSpacer = Components.vSpacerBase(2)

			val newGameButton = Components.buttonSimple("--> New_Game(); // Start a new game")
			newGameButton.onClick(::onNewGameButtonClick)

			val newGameButtonSpacer = Components.vSpacerBase(2)

			val loadGameButton = Components.buttonSimple("--> Load_Game(saves); // Load a saved game")
			loadGameButton.onClick(::onLoadGameButtonClick)

			val loadGameButtonSpacer = Components.vSpacerBase(2)

			val optionsButton = Components.buttonSimple("--> Options(configure); // Change game options")
			optionsButton.onClick(::onOptionsButtonClick)

			val optionsButtonSpacer = Components.vSpacerBase(2)

			val exitGameButton = Components.buttonSimple("--> Exit(0); // Return to OS")
			exitGameButton.onClickDelay(400L, ::onExitButtonClick)

			addComponents(
				header,
				continueButton,
				continueButtonSpacer,
				newGameButton,
				newGameButtonSpacer,
				loadGameButton,
				loadGameButtonSpacer,
				optionsButton,
				optionsButtonSpacer,
				exitGameButton
			)
		}

	// #####################################################
	// # UI-Events
	// #####################################################

	private fun onStartButtonClick() = startButtonComponent.detach().let {
		createStartMenu().apply {
			screen.addComponent(this)
		}
	}

	private fun onContinueButtonClick() = showFatalErrorScreen("Not implemented yet!")

	private fun onNewGameButtonClick() = navigateTo(NewGameScreen::class)

	private fun onLoadGameButtonClick() = showFatalErrorScreen("Not implemented yet!")

	private fun onOptionsButtonClick() = showFatalErrorScreen("Not implemented yet!")

	private fun onExitButtonClick(): Nothing = exitGame()
}
