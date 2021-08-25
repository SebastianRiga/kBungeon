package com.riga.bungeon.core.maps.bsp

import com.riga.bungeon.core.blocks.GameBlock
import com.riga.bungeon.core.blocks.GameBlockFactory.Civilized
import com.riga.bungeon.meta.extensions.branch
import com.riga.bungeon.meta.extensions.rng
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size

@Suppress("unused", "MemberVisibilityCanBePrivate")
data class BspRectangle(val position: Position, val size: Size) {

	constructor(x: Int, y: Int, width: Int, height: Int) : this(
		Position.create(x, y),
		Size.create(width, height)
	)

	val width: Int
		get() = size.width

	val height: Int
		get() = size.height

	val left: Int
		get() = position.x

	val right: Int
		get() = left + width

	val top: Int
		get() = position.y

	val bottom: Int
		get() = top + height

	val center: Position
		get() = Position.create(right / 2, bottom / 2)

	fun randomPosition(): Position {
		rng(left + 1, right - 2).let { x: Int ->
			rng(top + 1, bottom - 2).let { y: Int ->
				return Position.create(x, y)
			}
		}
	}

	fun makeBlocks(hasWalls: Boolean): Map<Position3D, GameBlock> = mutableMapOf<Position3D, GameBlock>().apply {
		(left..right).forEach { x ->
			(top..bottom).forEach { y ->
				val pos = Position3D.create(x, y, 0)

				this[pos] = hasWalls.branch({
					if (x == left && y == top) {
						Civilized.newWallTopLeft()
					} else if (x == right && y == top) {
						Civilized.newWallTopRight()
					} else if (x == left && y == bottom) {
						Civilized.newWallBottomLeft()
					} else if (x == right && y == bottom) {
						Civilized.newWallBottomRight()
					} else if (x == left || x == right) {
						Civilized.newWallVertical()
					} else if (y == top || y == bottom) {
						Civilized.newWallHorizontal()
					} else {
						Civilized.newFloor()
					}
				}, {
					Civilized.newFloor()
				})
			}
		}
	}
}