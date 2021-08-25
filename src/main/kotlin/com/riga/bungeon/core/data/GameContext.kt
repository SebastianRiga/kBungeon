package com.riga.bungeon.core.data

import com.riga.bungeon.core.objects.actors.GOPlayer
import com.riga.bungeon.meta.aliases.GameObject
import com.riga.bungeon.screens.internal.BaseScreen
import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.uievent.UIEvent

data class GameContext(
	val world: GameWorld,
	val screen: BaseScreen,
	val uiEvent: UIEvent,
	val player: GameObject<GOPlayer>
) : Context
