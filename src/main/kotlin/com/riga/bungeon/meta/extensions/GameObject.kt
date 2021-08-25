package com.riga.bungeon.meta.extensions

import com.riga.bungeon.core.attributes.APosition
import com.riga.bungeon.core.attributes.ATile
import com.riga.bungeon.core.data.GameWorld
import com.riga.bungeon.exceptions.AttributeDoesNotExistsException
import com.riga.bungeon.meta.aliases.AnyGameObject
import org.hexworks.amethyst.api.Attribute
import org.hexworks.zircon.api.data.Position3D
import kotlin.reflect.KClass

var AnyGameObject.position
	get() = findAttributeOrThrow(APosition::class).position
	set(value) {
		findAttribute(APosition::class).map {
			it.position = value
		}
	}

val AnyGameObject.tile
	get() = findAttributeOrThrow(ATile::class).tile

fun <T : Attribute> AnyGameObject.findAttributeOrThrow(clazz: KClass<T>): T = findAttribute(clazz).orElseThrow {
	AttributeDoesNotExistsException("No attribute of class ${clazz.simpleName} exists on game object ${this.name}")
}

fun AnyGameObject.screenPosition(world: GameWorld): Position3D = position - world.visibleOffset