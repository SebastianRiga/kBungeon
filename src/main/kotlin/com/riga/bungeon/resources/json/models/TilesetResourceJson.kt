package com.riga.bungeon.resources.json.models

import org.hexworks.cobalt.core.api.UUID
import org.hexworks.zircon.api.resource.TilesetResource
import org.hexworks.zircon.internal.resource.TileType
import org.hexworks.zircon.internal.resource.TilesetSourceType
import org.hexworks.zircon.internal.resource.TilesetType

data class TilesetResourceJson(
	val id: String,
	val tileType: Int,
	val tilesetType: Int,
	val tilesetSourceType: Int,
	val width: Int,
	val height: Int,
	val path: String
) {
	fun serialize(): TilesetResource = kotlin.run {
		object : TilesetResource {
			override val height: Int
				get() = this@TilesetResourceJson.height
			override val id: UUID
				get() = UUID.fromString(this@TilesetResourceJson.id)
			override val path: String
				get() = this@TilesetResourceJson.path
			override val tileType: TileType
				get() = TileType.values()[this@TilesetResourceJson.tileType]
			override val tilesetSourceType: TilesetSourceType
				get() = TilesetSourceType.values()[this@TilesetResourceJson.tilesetSourceType]
			override val tilesetType: TilesetType
				get() = TilesetType.values()[this@TilesetResourceJson.tilesetType]
			override val width: Int
				get() = this@TilesetResourceJson.width
		}
	}
}