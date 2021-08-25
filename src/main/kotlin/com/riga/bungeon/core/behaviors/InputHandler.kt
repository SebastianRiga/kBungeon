package com.riga.bungeon.core.behaviors

import com.riga.bungeon.core.behaviors.internal.GameBehavior
import com.riga.bungeon.core.data.GameContext
import com.riga.bungeon.core.facets.messages.MoveGameObjectMessage
import com.riga.bungeon.meta.extensions.position
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

object InputHandler : GameBehavior() {
	override suspend fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean = context.let {
		if (it.uiEvent is KeyboardEvent) {
			it.player.apply {
				val newPosition = when (it.uiEvent.code) {
					KeyCode.KEY_W -> position.withRelativeY(-1)
					KeyCode.KEY_A -> position.withRelativeX(-1)
					KeyCode.KEY_S -> position.withRelativeY(1)
					KeyCode.KEY_D -> position.withRelativeX(1)
					else -> position
				}
				receiveMessage(MoveGameObjectMessage(it, this, newPosition))
			}
		}
		true
	}
}