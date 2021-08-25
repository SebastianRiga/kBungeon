package com.riga.bungeon.meta.extensions

import org.hexworks.zircon.api.data.Size3D

val Size3D.halfSize
	get() = Size3D.create(xLength / 2, yLength / 2, zLength / 2)