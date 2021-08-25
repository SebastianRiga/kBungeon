package com.riga.bungeon.screens.internal.interfaces

import com.riga.bungeon.components.GameComponentBuilder
import com.riga.bungeon.components.SpacerBuilder
import com.riga.bungeon.components.SpacerDirection
import com.riga.bungeon.core.GameController
import org.hexworks.zircon.api.Components

interface ComponentsExtended {
	private fun spacer(size: Int, direction: SpacerDirection) = SpacerBuilder.newBuilder(size, direction)

	fun Components.vSpacer(size: Int) = spacer(size, SpacerDirection.VERTICAL)

	fun Components.vSpacerBase(size: Int) = spacer(size, SpacerDirection.VERTICAL).build()

	fun Components.hSpacer(size: Int) = spacer(size, SpacerDirection.HORIZONTAL)

	fun Components.hSpacerBase(size: Int) = spacer(size, SpacerDirection.HORIZONTAL).build()

	fun Components.buttonSimple(title: String) = button().withText(title).build()

	fun Components.gameComponent(gameController: GameController) = GameComponentBuilder.newBuilder(gameController)
}