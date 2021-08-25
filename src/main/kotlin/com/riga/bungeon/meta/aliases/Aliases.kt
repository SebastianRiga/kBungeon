package com.riga.bungeon.meta.aliases

import org.hexworks.amethyst.api.entity.Entity
import com.riga.bungeon.core.data.GameContext
import com.riga.bungeon.core.objects.actors.GOPlayer
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType

typealias GameObject<T> = Entity<T, GameContext>
typealias AnyGameObject = GameObject<EntityType>
typealias GameObjectBuilder<T> = EntityBuilder<T, GameContext>

typealias GO_PLAYER = GameObject<GOPlayer>