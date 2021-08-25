package com.riga.bungeon.core.maps.bsp

import com.riga.bungeon.core.blocks.GameBlock
import com.riga.bungeon.meta.extensions.addAll
import com.riga.bungeon.meta.extensions.branch
import com.riga.bungeon.meta.extensions.rng
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size
import kotlin.math.abs
import kotlin.random.Random

@Suppress("MemberVisibilityCanBePrivate")
class BspLeaf(val x: Int, val y: Int, val width: Int, val height: Int) {

	companion object {
		fun rootLeaf(width: Int, height: Int) = BspLeaf(0, 0, width, height)
	}

	var room: BspRectangle? = null
	var leftChild: BspLeaf? = null
	var rightChild: BspLeaf? = null
	var intersections: MutableList<BspRectangle> = mutableListOf()

	val isSplit: Boolean
		get() = leftChild != null && rightChild != null

	val isNotSplit: Boolean
		get() = !isSplit

	fun canSplit(maxLeafSize: Int): Boolean = (width > maxLeafSize || height > maxLeafSize)

	fun split(minLeafSize: Int): Boolean {
		if (leftChild != null && rightChild != null) {
			return true
		}

		val splitHorizontally = (width > height && width / height >= 1.25).branch({ false }) {
			(height > width && height / width >= 1.25).branch({ true }) {
				Math.random() > 0.5
			}
		}

		val max = (splitHorizontally.branch({ height }, { width }) - minLeafSize)

		if (max <= minLeafSize) {
			return false
		}

		val splitValue = Random.nextInt(minLeafSize, max)

		splitHorizontally.branch({
			leftChild = BspLeaf(x, y, width, splitValue)
			rightChild = BspLeaf(x, y + splitValue, width, height - splitValue)
		}, {
			leftChild = BspLeaf(x, y, splitValue, height)
			rightChild = BspLeaf(x + splitValue, y, width - splitValue, height)
		})

		return true
	}

	fun createRooms(): BspLeaf {
		if (leftChild != null || rightChild != null) {
			leftChild?.createRooms()
			rightChild?.createRooms()
			createIntersections()
		} else {
			val roomWidth = Random.nextInt(3, width - 2)
			val roomHeight = Random.nextInt(3, height - 2)
			val size = Size.create(roomWidth, roomHeight)

			val roomX = Random.nextInt(x, (x + width) - roomWidth - 1)
			val roomY = Random.nextInt(y, (y + height) - roomHeight - 1)
			val position = Position.create(roomX, roomY)

			room = BspRectangle(position, size)
		}

		return this
	}

	fun getNextRoom(): BspRectangle? {
		room?.let {
			return it
		}
		
		var leftRoom: BspRectangle? = null
		var rightRoom: BspRectangle? = null

		leftChild?.getNextRoom()?.let { left ->
			leftRoom = left
		}

		rightChild?.getNextRoom()?.let { right ->
			rightRoom = right
		}

		return if (leftRoom == null && rightRoom == null) {
			null
		} else if (rightRoom == null) {
			leftRoom
		} else if (leftRoom == null) {
			rightRoom
		} else if (rng() > .5) {
			leftRoom
		} else {
			rightRoom
		}
	}

	fun createIntersections() {
		leftChild?.getNextRoom()?.let { leftRoom ->
			rightChild?.getNextRoom()?.let { rightRoom ->
				val posLeft = leftRoom.randomPosition()
				val posRight = rightRoom.randomPosition()

				val width = posRight.x - posLeft.x
				val height = posRight.y - posLeft.y

				if (width < 0) {
					if (height < 0) {
						if (rng() < .5) {
							intersections.addAll(
								BspRectangle(posRight.x, posLeft.y, abs(width), 1),
								BspRectangle(posRight.x, posRight.y, 1, abs(height))
							)
						} else {
							intersections.addAll(
								BspRectangle(posRight.x, posRight.y, abs(width), 1),
								BspRectangle(posLeft.x, posRight.y, 1, abs(height))
							)
						}
					} else if (height > 0) {
						if (rng() < .5) {
							intersections.addAll(
								BspRectangle(posRight.x, posLeft.y, abs(width), 1),
								BspRectangle(posRight.x, posLeft.y, 1, abs(height))
							)
						} else {
							intersections.addAll(
								BspRectangle(posRight.x, posRight.y, abs(width), 1),
								BspRectangle(posLeft.x, posLeft.y, 1, abs(height))
							)
						}
					} else {
						intersections.add(BspRectangle(posRight.x, posRight.y, abs(width), 1))
					}
				} else if (width > 0) {
					if (height < 0) {
						if (rng() < 0.5) {
							intersections.addAll(
								BspRectangle(posLeft.x, posRight.y, abs(width), 1),
								BspRectangle(posLeft.x, posRight.y, 1, abs(height))
							)
						} else {
							intersections.addAll(
								BspRectangle(posLeft.x, posLeft.y, abs(width), 1),
								BspRectangle(posRight.x, posRight.y, 1, abs(height))
							)
						}
					} else if (height > 0) {
						if (rng() < 0.5) {
							intersections.addAll(
								BspRectangle(posLeft.x, posLeft.y, abs(width), 1),
								BspRectangle(posRight.x, posLeft.y, 1, abs(height))
							)
						} else {
							intersections.addAll(
								BspRectangle(posLeft.x, posRight.y, abs(width), 1),
								BspRectangle(posLeft.x, posLeft.y, 1, abs(height))
							)
						}
					} else {
						intersections.add(BspRectangle(posLeft.x, posLeft.y, abs(width), 1))
					}
				} else {
					if (height < 0) {
						intersections.add(BspRectangle(posRight.x, posRight.y, 1, abs(height)))
					} else if (height > 0) {
						intersections.add(BspRectangle(posLeft.x, posLeft.y, 1, abs(height)))
					} else {
						/* no-op */
					}
				}
			}
		}
	}

	fun makeBlocks(): Map<Position3D, GameBlock> = mutableMapOf<Position3D, GameBlock>().apply {
		room?.let {
			putAll(it.makeBlocks(true))
		}

		leftChild?.let {
			putAll(it.makeBlocks())
		}

		rightChild?.let {
			putAll(it.makeBlocks())
		}

		intersections.forEach {
			putAll(it.makeBlocks(false))
		}
	}
}