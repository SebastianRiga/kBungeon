package com.riga.bungeon.core

import com.riga.bungeon.core.data.GameWorld
import com.riga.bungeon.core.objects.GameObjectFactory
import com.riga.bungeon.core.maps.CavedBuilder

class GameBuilder private constructor() {

	private var player = GameObjectFactory.newPlayer()
	private var world = CavedBuilder.emptyWorld()

	companion object {
		fun newBuilder(init: GameBuilder.() -> Unit): GameController = GameBuilder()
			.apply(init)
			.prepare()
			.player()
			.build()
	}

	fun world(config: () -> GameWorld) = let { world = config() }

	fun build(): GameController = GameController(world, player)

	private fun player(): GameBuilder = also {
		player = GameObjectFactory.newPlayer().also { player ->
			world.getFirstEmptyPositionByBlocks().also { position ->
				world.putObject(player, position)
			}
		}
	}

	private fun prepare(): GameBuilder = also {
		world.scrollUpBy(world.actualSize.zLength)
	}
}