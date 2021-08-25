package com.riga.bungeon.core.blocks

import com.riga.bungeon.core.tiles.TileRepository
import com.riga.bungeon.meta.aliases.AnyGameObject
import com.riga.bungeon.meta.extensions.collides
import com.riga.bungeon.meta.extensions.tile
import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock

class GameBlock(private var tile: Tile, private val entities: MutableList<AnyGameObject> = mutableListOf()) :
	BaseBlock<Tile>(TileRepository.Meta.EMPTY, persistentMapOf(BlockTileType.CONTENT to tile)) {
		
	val hasCollision: Boolean
		get() = tile.collides
	
	val hasGameObjects: Boolean
		get() = entities.isNotEmpty()
	
	val isEmptyFloor: Boolean
		get() = !hasCollision && !hasGameObjects && tile != TileRepository.Meta.EMPTY
	
	val gameObjects: List<AnyGameObject>
		get() = entities.toList()
	
	fun addEntity(entity: AnyGameObject) = entities.add(entity).also { 
		updateDisplay()
	}
	
	fun removeEntity(entity: AnyGameObject) = entities.remove(entity).also { 
		updateDisplay()
	}
	
	private fun updateDisplay() = entities.map { it.tile }.run { 
		content = when {
			contains(TileRepository.Actors.PLAYER) -> TileRepository.Actors.PLAYER
			isNotEmpty() -> first()
			else -> tile
		}
	}
}