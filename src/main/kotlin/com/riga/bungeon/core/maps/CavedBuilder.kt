package com.riga.bungeon.core.maps

import com.riga.bungeon.core.blocks.GameBlock
import com.riga.bungeon.core.blocks.GameBlockFactory.Caves
import com.riga.bungeon.core.data.GameWorld
import com.riga.bungeon.core.maps.internal.WorldBuilder
import com.riga.bungeon.meta.extensions.branch
import com.riga.bungeon.meta.extensions.neighbours2D
import com.riga.bungeon.meta.extensions.runIfElementExists
import org.hexworks.zircon.api.data.Position3D

class CavedBuilder private constructor() : WorldBuilder() {

	private var smoothOutIterations: Int = 8

	companion object {
		fun newWorld(init: CavedBuilder.() -> Unit): GameWorld = CavedBuilder()
			.apply(init)
			.randomize()
			.smoothOut()
			.build()
		
		fun emptyWorld(): GameWorld = newWorld {
			/* no-op */
		}
	}

	fun smoothOutIterations(config: () -> Int): Unit = config().run { smoothOutIterations = this }

	private fun randomize(): CavedBuilder = iteratePositions { position ->
		blocks[position] = (Math.random() < 0.5).branch(Caves::newFloor, Caves::newWall)
	}.run {
		return@run this@CavedBuilder
	}

	private fun smoothOut(): CavedBuilder = mutableMapOf<Position3D, GameBlock>().run {
		repeat(smoothOutIterations) {
			iteratePositions { position3D ->
				var foundFloors = 0
				var foundWalls = 0

				position3D.neighbours2D(includeSelf = true, shuffle = true).forEach { neighbour ->
					blocks.runIfElementExists(neighbour) { block ->
						block.hasCollision.branch({ foundWalls++ }, { foundFloors++ })
					}
				}
				this[position3D] = (foundFloors >= foundWalls)
					.branch(Caves::newFloor, Caves::newWall)
			}
			blocks = this
		}
		return@run this@CavedBuilder
	}

	override fun build(): GameWorld = GameWorld(blocks, visibleSize, actualSize)
}