package com.riga.bungeon.meta.extensions

import org.hexworks.zircon.api.data.Position3D

fun Position3D.neighbours2D(includeSelf: Boolean = false, shuffle: Boolean = false): List<Position3D> =
	(-1..1).flatMap { x ->
		(-1..1).map { y ->
			withRelative(Position3D.create(x, y, 0))
		}.also {
			if (!includeSelf) {
				minus(this)
			}
		}.also {
			if (shuffle) {
				it.shuffled()
			}
		}
	}