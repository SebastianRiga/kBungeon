package com.riga.bungeon.core.data

import com.riga.bungeon.core.blocks.GameBlock
import com.riga.bungeon.meta.aliases.AnyGameObject
import com.riga.bungeon.meta.extensions.position
import org.hexworks.amethyst.api.Engine
import org.hexworks.amethyst.internal.TurnBasedEngine
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.GameArea

class GameWorld(blocks: Map<Position3D, GameBlock>, visibleSize: Size3D, actualSize: Size3D) : GameArea<Tile, GameBlock>
by GameAreaBuilder.newBuilder<Tile, GameBlock>()
	.withVisibleSize(visibleSize)
	.withActualSize(actualSize)
	.build() {

	val engine: TurnBasedEngine<GameContext> = Engine.create()

	init {
		blocks.forEach { (position, block) ->
			setBlockAt(position, block)
			block.gameObjects.forEach {
				engine.addEntity(it)
				it.position = position
			}
		}
	}

	fun putObject(obj: AnyGameObject, position: Position3D) {
		obj.apply {
			this.position = position
			engine.addEntity(this)
			fetchBlockAt(position).map {
				it.addEntity(this)
			}
		}
	}

	fun getRandomEmptyPosition(
		offset: Position3D,
		size: Size3D,
		attempts: Int = size.xLength * size.yLength
	): Position3D? {
		var attempt = 0

		while (attempt < attempts) {
			val x = (Math.random() * size.xLength).toInt() + offset.x
			val y = (Math.random() * size.yLength).toInt() + offset.y
			val z = (Math.random() * size.zLength).toInt() + offset.z

			val tmpPos = Position3D.create(x, y, z)
			fetchBlockAtOrNull(tmpPos)?.let {
				if (it.isEmptyFloor) {
					return tmpPos
				}
			}
			attempt++
		}

		return null
	}

	fun getFirstEmptyPosition(): Position3D = blocks
		.filter {
			it.value.isEmptyFloor
		}
		.firstNotNullOf {
			it.key
		}

	fun getFirstEmptyPosition(z: Int = 1): Maybe<Position3D> {
		(0..actualSize.yLength).forEach { y ->
			(0..actualSize.xLength).forEach { x ->
				Position3D.create(x, y, z).apply {
					fetchBlockAtOrNull(this)?.let {
						if (it.isEmptyFloor) {
							return Maybe.of(this)
						}
					}
				}
			}
		}
		return Maybe.empty()
	}
}
