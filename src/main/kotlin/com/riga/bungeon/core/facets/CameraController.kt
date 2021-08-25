package com.riga.bungeon.core.facets

import com.riga.bungeon.core.facets.internal.GameFacet
import com.riga.bungeon.core.facets.messages.MoveCameraMessage
import com.riga.bungeon.meta.extensions.halfSize
import com.riga.bungeon.meta.extensions.position
import com.riga.bungeon.meta.extensions.screenPosition
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response

object CameraController : GameFacet<MoveCameraMessage>(MoveCameraMessage::class) {

	override suspend fun receive(message: MoveCameraMessage): Response = run {
		message.apply {
			context.apply {
				val sourcePosition = source.position
				val sourceScreenPosition = source.screenPosition(world)
				val halfWorldSize = world.visibleSize.halfSize

				when {
					previousGameObjectPosition3D.y > sourcePosition.y && sourceScreenPosition.y < halfWorldSize.yLength -> {
						world.scrollOneBackward()
					}
					previousGameObjectPosition3D.y < sourcePosition.y && sourceScreenPosition.y > halfWorldSize.yLength -> {
						world.scrollOneForward()
					}
					previousGameObjectPosition3D.x > sourcePosition.x && sourceScreenPosition.x < halfWorldSize.xLength -> {
						world.scrollOneLeft()
					}
					previousGameObjectPosition3D.x < sourcePosition.x && sourceScreenPosition.x > halfWorldSize.xLength -> {
						world.scrollOneRight()
					}
				}
			}
		}
		Consumed
	}
}