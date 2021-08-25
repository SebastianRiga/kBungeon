package com.riga.bungeon.core.facets.internal

import com.riga.bungeon.core.data.GameContext
import com.riga.bungeon.meta.aliases.AnyGameObject
import org.hexworks.amethyst.api.Message
import org.hexworks.amethyst.api.MessageResponse

abstract class GameMessage(override val context: GameContext, override val source: AnyGameObject) :
	Message<GameContext> {
}
