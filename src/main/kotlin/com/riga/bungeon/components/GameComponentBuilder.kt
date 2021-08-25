package com.riga.bungeon.components

import com.riga.bungeon.core.GameController
import com.riga.bungeon.core.tiles.TileRepository
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.internal.game.impl.GameAreaComponentRenderer

object GameComponentBuilder {
	fun newBuilder(gameController: GameController) = Components.panel()
		.withComponentRenderer(GameAreaComponentRenderer(
			gameController.gameWorld,
			ProjectionMode.TOP_DOWN.toProperty(),
			TileRepository.Meta.FILLER
		))
}