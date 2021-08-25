package com.riga.bungeon.components

import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.builder.component.HeaderBuilder

object SpacerBuilder {
	
	fun newBuilder(size: Int, direction: SpacerDirection): HeaderBuilder {
		
		val width = if (direction === SpacerDirection.HORIZONTAL) size else 1
		val height = if (direction === SpacerDirection.VERTICAL) size else 1
		
		return Components.header()
			.withSize(width, height)
	}
}