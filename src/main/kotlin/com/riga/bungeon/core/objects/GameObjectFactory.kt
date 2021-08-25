package com.riga.bungeon.core.objects

import com.riga.bungeon.core.objects.actors.GOPlayer
import com.riga.bungeon.core.attributes.APosition
import com.riga.bungeon.core.attributes.ATile
import com.riga.bungeon.core.tiles.TileRepository
import com.riga.bungeon.meta.aliases.GameObjectBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType

object GameObjectFactory {

	private fun <T : EntityType> newGameObject(type: T, initializer: GameObjectBuilder<T>.() -> Unit) =
		newEntityOfType(type, initializer)
	
	fun newPlayer() = newGameObject(GOPlayer) {
		attributes(APosition(), ATile(TileRepository.Actors.PLAYER))
		behaviors()
		facets()
	}
}