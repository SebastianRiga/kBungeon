package com.riga.bungeon.system.json.adapter

import com.riga.bungeon.system.json.models.TilesetResourceJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.hexworks.zircon.api.resource.TilesetResource

class TilesetResourceJsonAdapter {

	@ToJson
	fun toJson(obj: TilesetResource) = obj.deserialize()

	@FromJson
	fun fromJson(json: TilesetResourceJson) = json.serialize()
}

fun TilesetResource.deserialize() = TilesetResourceJson(
	id.toString(),
	tileType.ordinal,
	tilesetType.ordinal,
	tilesetSourceType.ordinal,
	width,
	height,
	path
)

