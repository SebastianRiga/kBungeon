package com.riga.bungeon.meta.extensions

import com.riga.bungeon.meta.classes.BackingField
import org.hexworks.zircon.api.data.Tile

var Tile.collides: Boolean by BackingField { false }