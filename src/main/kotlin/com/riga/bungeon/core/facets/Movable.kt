package com.riga.bungeon.core.facets

import com.riga.bungeon.core.facets.internal.GameFacet
import com.riga.bungeon.core.facets.messages.MoveCameraMessage
import com.riga.bungeon.core.facets.messages.MoveGameObjectMessage
import com.riga.bungeon.core.objects.actors.GOPlayer
import com.riga.bungeon.meta.extensions.position
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.MessageResponse
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response

object Movable : GameFacet<MoveGameObjectMessage>(MoveGameObjectMessage::class) {

	override suspend fun receive(message: MoveGameObjectMessage): Response {
		val (context, entity, position) = message
		val previousGameObjectPosition = entity.position

		context.run {
			if (world.move(entity, position)) {
				if (entity.type == GOPlayer) {
					return MessageResponse(
						MoveCameraMessage(
							context,
							entity,
							previousGameObjectPosition
						)
					)
				}
				return Consumed
			}
			return Pass
		}
	}
}