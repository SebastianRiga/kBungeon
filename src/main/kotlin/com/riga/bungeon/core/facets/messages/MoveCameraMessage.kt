package com.riga.bungeon.core.facets.messages

import com.riga.bungeon.core.data.GameContext
import com.riga.bungeon.core.facets.internal.GameMessage
import com.riga.bungeon.meta.aliases.AnyGameObject
import org.hexworks.zircon.api.data.Position3D

data class MoveCameraMessage(
	override val context: GameContext,
	override val source: AnyGameObject,
	val previousGameObjectPosition3D: Position3D
) : GameMessage(context, source)
