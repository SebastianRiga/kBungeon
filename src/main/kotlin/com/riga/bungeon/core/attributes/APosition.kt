package com.riga.bungeon.core.attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.zircon.api.data.Position3D

class APosition(position: Position3D = Position3D.unknown()): BaseAttribute() {
	
	private val positionProp = position.toProperty()
	
	var position: Position3D by positionProp.asDelegate()
}
