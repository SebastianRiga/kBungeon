package com.riga.bungeon.screens

import com.riga.bungeon.core.GameBuilder
import com.riga.bungeon.core.GameController
import com.riga.bungeon.core.data.GameConfig
import com.riga.bungeon.core.maps.BspBuilder
import com.riga.bungeon.screens.internal.BaseScreen
import com.riga.bungeon.screens.internal.Bundle
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.Processed

class PlayScreen(tileGrid: TileGrid, gameConfig: GameConfig, bundle: Bundle?) : BaseScreen(tileGrid, gameConfig, bundle) {

	private val sidePanelWidth: Int = (gameConfig.windowWidth * 0.25).toInt()

	private val logPanelWidth: Int = (gameConfig.windowWidth * 0.75).toInt()
	private val logPanelHeight: Int = (gameConfig.windowHeight * 0.2).toInt()

	private val gameAreaWidth: Int = (gameConfig.windowWidth * 0.75).toInt()
	private val gameAreaHeight: Int = (gameConfig.windowHeight * 0.8).toInt()

	private val gameAreaSize3D = Size3D.create(
		xLength = gameAreaWidth,
		yLength = gameAreaHeight,
		zLength = gameConfig.dungeonDepth
	)

	private val gameController: GameController

	init {

		gameController = GameBuilder.newBuilder {
			world {
				BspBuilder.newWorld {
					visibleSize { gameAreaSize3D }
					actualSize { gameConfig.worldSize }
				}
			}
		}

		screen.apply {
			Components.panel()
				.withSize(sidePanelWidth, gameConfig.windowHeight)
				.withDecorations(box())
				.withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
				.build().also { addComponent(it) }

			Components.panel()
				.withSize(logPanelWidth, logPanelHeight)
				.withDecorations(box(title = "Log"))
				.withAlignmentWithin(screen, ComponentAlignment.BOTTOM_LEFT)
				.build().also { addComponent(it) }

			Components.gameComponent(gameController)
				.withSize(gameAreaSize3D.to2DSize())
				.withAlignmentWithin(screen, ComponentAlignment.TOP_LEFT)
				.build().also { addComponent(it) }
			
			handleKeyboardEvents(KeyboardEventType.KEY_PRESSED) { event, _ ->
				gameController.update(this@PlayScreen, event)
				Processed
			}
		}
	}

}