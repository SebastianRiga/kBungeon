package com.riga.bungeon.core.attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.zircon.api.data.Tile

data class ATile(val tile: Tile = Tile.empty()): BaseAttribute() 