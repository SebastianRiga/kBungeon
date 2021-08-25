package com.riga.bungeon.core.facets.internal

import com.riga.bungeon.core.data.GameContext
import org.hexworks.amethyst.api.base.BaseFacet
import kotlin.reflect.KClass

abstract class GameFacet<M : GameMessage>(messageType: KClass<M>) : BaseFacet<GameContext, M>(messageType) 