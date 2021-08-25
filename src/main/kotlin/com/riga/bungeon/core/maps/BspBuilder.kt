package com.riga.bungeon.core.maps

import com.riga.bungeon.core.data.GameWorld
import com.riga.bungeon.core.maps.bsp.BspLeaf
import com.riga.bungeon.core.maps.internal.WorldBuilder
import com.riga.bungeon.meta.extensions.addAll

class BspBuilder private constructor() : WorldBuilder() {

	companion object {
		const val MIN_LEAF_SIZE = 12
		const val MAX_LEAF_SIZE = 24

		fun newWorld(init: BspBuilder.() -> Unit): GameWorld = BspBuilder()
			.apply(init)
			.build()
	}

	override fun build(): GameWorld {
		val root = BspLeaf.rootLeaf(actualSize.xLength, actualSize.yLength)
		val leaves = mutableListOf(root)
		var continueSplitting = true

		while (continueSplitting) {
			continueSplitting = false

			val clone = leaves.toMutableList()
			clone.forEach { leaf ->
				if (leaf.isNotSplit && leaf.canSplit(MAX_LEAF_SIZE) && leaf.split(MIN_LEAF_SIZE)) {
					leaves.addAll(leaf.leftChild!!, leaf.rightChild!!)
					continueSplitting = true
				}
			}
		}

		root.createRooms()
			.makeBlocks()
			.apply {
				renewBlocks(this)
			}

		return GameWorld(blocks, visibleSize, actualSize)
	}
}