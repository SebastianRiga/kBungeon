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

class GameWorld(blocks: Map<Position3D, GameBlock>, visibleSize: Size3D, actualSize: Size3D) :
	GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
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
	

	fun getFirstEmptyPositionByBlocks(): Position3D = blocks
		.filter {
			it.value.isEmptyFloor
		}
		.firstNotNullOf {
			it.key
		}
	
	fun move(obj: AnyGameObject, target: Position3D): Boolean = let {
		fetchBlockAtOrNull(obj.position)?.let { currentBlock ->
			fetchBlockAtOrNull(target)?.let { newBlock -> 
				if (newBlock.hasCollision) {
					return false
				}
				
				currentBlock.removeEntity(obj)
				obj.position = target
				newBlock.addEntity(obj)
				return true
			}
		}
		return false
	}
}
