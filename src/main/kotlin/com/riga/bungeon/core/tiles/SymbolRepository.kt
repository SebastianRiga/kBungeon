package com.riga.bungeon.core.tiles

import org.hexworks.zircon.api.graphics.Symbols

object SymbolRepository {
	
	object Actors {
		const val PLAYER = '@'
	}
	
	object Environment  {
		object Caves {
			const val FLOOR = Symbols.INTERPUNCT
			const val WALL = '#'
		}
		
		object Civilized {
			const val WALL_TOP_LEFT = Symbols.DOUBLE_LINE_TOP_LEFT_CORNER
			const val WALL_TOP_RIGHT = Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER
			const val WALL_BOTTOM_LEFT = Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER
			const val WALL_BOTTOM_RIGHT = Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER
			const val WALL_VERTICAL = Symbols.DOUBLE_LINE_VERTICAL
			const val WALL_HORIZONTAL = Symbols.DOUBLE_LINE_HORIZONTAL
		}
	}
}