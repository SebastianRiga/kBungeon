package com.riga.bungeon.system.json.adapter

import com.riga.bungeon.system.json.models.TileColorJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.hexworks.zircon.api.color.TileColor

class TileColorJsonAdapter {

	@FromJson
	fun fromJson(obj: TileColorJson) = obj.serialize()

	@ToJson
	fun toJson(obj: TileColor) = obj.deserialize()
}

fun TileColor.deserialize(): TileColorJson = TileColorJson(red, green, blue, alpha)

