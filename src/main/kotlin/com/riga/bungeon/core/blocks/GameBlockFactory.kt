package com.riga.bungeon.core.blocks

import com.riga.bungeon.core.tiles.TileRepository

object GameBlockFactory {
	
	object Meta {
		fun newEmpty() = GameBlock(TileRepository.Meta.EMPTY)
		
		fun newFiller() = GameBlock(TileRepository.Meta.EMPTY)
	}
	
	object Caves {
		fun newFloor() = GameBlock(TileRepository.Caves.FLOOR)
		fun newWall() = GameBlock(TileRepository.Caves.WALL_TOP_LEFT)
	}

	object Civilized {
		fun newFloor() = GameBlock(TileRepository.Civilized.FLOOR)
		
		fun newWallTopLeft() = GameBlock(TileRepository.Civilized.WALL_TOP_LEFT)

		fun newWallTopRight() = GameBlock(TileRepository.Civilized.WALL_TOP_RIGHT)

		fun newWallBottomLeft() = GameBlock(TileRepository.Civilized.WALL_BOTTOM_LEFT)

		fun newWallBottomRight() = GameBlock(TileRepository.Civilized.WALL_BOTTOM_RIGHT)

		fun newWallVertical() = GameBlock(TileRepository.Civilized.WALL_VERTICAL)

		fun newWallHorizontal() = GameBlock(TileRepository.Civilized.WALL_HORIZONTAL)
	}
	}



