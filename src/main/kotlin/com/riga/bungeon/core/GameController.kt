package com.riga.bungeon.core

import com.riga.bungeon.core.data.GameContext
import com.riga.bungeon.core.data.GameWorld
import com.riga.bungeon.core.objects.actors.GOPlayer
import com.riga.bungeon.meta.aliases.GameObject
import com.riga.bungeon.screens.internal.BaseScreen
import org.hexworks.zircon.api.uievent.UIEvent

class GameController constructor(val gameWorld: GameWorld, val player: GameObject<GOPlayer>) {

	fun update(screen: BaseScreen, uiEvent: UIEvent) = GameContext(
		gameWorld,
		screen,
		uiEvent,
		player
	).run {
		gameWorld.engine.executeTurn(this)
	}
}