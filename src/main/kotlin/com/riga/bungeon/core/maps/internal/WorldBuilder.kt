package com.riga.bungeon.core.maps.internal

import com.riga.bungeon.core.blocks.GameBlock
import com.riga.bungeon.core.data.GameWorld
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D

abstract class WorldBuilder {
	
	protected var blocks: MutableMap<Position3D, GameBlock> = mutableMapOf()
	protected var actualSize: Size3D = Size3D.create(1, 1, 1)
	protected var visibleSize: Size3D = Size3D.create(1, 1, 1)
	
	val width: Int
		get() = actualSize.xLength
	
	val height: Int
		get() = actualSize.yLength

	fun visibleSize(config: () -> Size3D): Unit = config().run { visibleSize = this }

	fun actualSize(config: () -> Size3D): Unit = config().run { actualSize = this }
	
	protected abstract fun build(): GameWorld
	
	protected fun renewBlocks(blocks: Map<Position3D, GameBlock>) = this.blocks.apply { 
		clear()
		putAll(blocks)
	}

	protected fun iteratePositions(block: (Position3D) -> Unit) = actualSize.fetchPositions().forEach(block)
}