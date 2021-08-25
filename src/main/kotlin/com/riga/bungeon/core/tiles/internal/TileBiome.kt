package com.riga.bungeon.core.tiles.internal

import org.hexworks.zircon.api.data.Tile

@Suppress("PropertyName")
interface TileBiome {
	val FLOOR: Tile
	val WALL_TOP_LEFT: Tile
	val WALL_TOP_RIGHT: Tile
	val WALL_BOTTOM_LEFT: Tile
	val WALL_BOTTOM_RIGHT: Tile
	val WALL_VERTICAL: Tile
	val WALL_HORIZONTAL: Tile
}