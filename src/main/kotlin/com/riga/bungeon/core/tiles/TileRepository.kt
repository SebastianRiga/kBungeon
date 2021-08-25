package com.riga.bungeon.core.tiles

import com.riga.bungeon.core.tiles.internal.TileBiome
import com.riga.bungeon.meta.extensions.collides
import com.riga.bungeon.system.ColorPallet
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.Symbols

object TileRepository {

	object Meta {
		val EMPTY = Tile.empty()

		val FILLER = Tile.newBuilder()
			.withCharacter(Symbols.BLOCK_SPARSE)
			.withForegroundColor(ColorPallet.PRIMARY_BACKGROUND_COLOR)
			.withBackgroundColor(ColorPallet.SECONDARY_BACKGROUND_COLOR)
			.buildCharacterTile()
	}

	object Actors {
		val PLAYER = Tile.newBuilder()
			.withCharacter(SymbolRepository.Actors.PLAYER)
			.withForegroundColor(ColorPallet.PRIMARY_FOREGROUND_COLOR)
			.withBackgroundColor(ColorPallet.SECONDARY_BACKGROUND_COLOR)
			.buildCharacterTile()
	}

	object Caves : TileBiome {
		override val FLOOR = createFloorTile(Symbols.INTERPUNCT)
		override val WALL_TOP_LEFT = createWallTile(SymbolRepository.Environment.Caves.WALL)
		override val WALL_TOP_RIGHT = createWallTile(SymbolRepository.Environment.Caves.WALL)
		override val WALL_BOTTOM_LEFT = createWallTile(SymbolRepository.Environment.Caves.WALL)
		override val WALL_BOTTOM_RIGHT = createWallTile(SymbolRepository.Environment.Caves.WALL)
		override val WALL_VERTICAL = createWallTile(SymbolRepository.Environment.Caves.WALL)
		override val WALL_HORIZONTAL = createWallTile(SymbolRepository.Environment.Caves.WALL)
	}

	object Civilized : TileBiome {
		override val FLOOR = createFloorTile(Symbols.BULLET_SMALL)
		override val WALL_TOP_LEFT = createWallTile(Symbols.DOUBLE_LINE_TOP_LEFT_CORNER)
		override val WALL_TOP_RIGHT = createWallTile(Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER)
		override val WALL_BOTTOM_LEFT = createWallTile(Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER)
		override val WALL_BOTTOM_RIGHT = createWallTile(Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER)
		override val WALL_VERTICAL = createWallTile(Symbols.DOUBLE_LINE_VERTICAL)
		override val WALL_HORIZONTAL = createWallTile(Symbols.DOUBLE_LINE_HORIZONTAL)
	}

	private fun createFloorTile(symbol: Char) = Tile.newBuilder()
		.withCharacter(symbol)
		.withForegroundColor(ColorPallet.PRIMARY_BACKGROUND_COLOR)
		.withBackgroundColor(ColorPallet.SECONDARY_BACKGROUND_COLOR)
		.buildCharacterTile().also { 
			it.collides = false
		}

	private fun createWallTile(symbol: Char) = Tile.newBuilder()
		.withCharacter(symbol)
		.withForegroundColor(ColorPallet.SECONDARY_FOREGROUND_COLOR)
		.withBackgroundColor(ColorPallet.SECONDARY_BACKGROUND_COLOR)
		.buildCharacterTile().also { 
			it.collides = true
		}
}