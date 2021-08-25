package com.riga.bungeon.core.facets.messages

import com.riga.bungeon.core.data.GameContext
import com.riga.bungeon.core.facets.internal.GameMessage
import com.riga.bungeon.meta.aliases.AnyGameObject
import org.hexworks.zircon.api.data.Position3D

data class MoveGameObjectMessage(
	override val context: GameContext,
	override val source: AnyGameObject,
	val newPosition: Position3D
) : GameMessage(context, source)
