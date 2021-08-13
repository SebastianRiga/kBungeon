package com.riga.bungeon.resources.json.adapter

import com.riga.bungeon.resources.json.models.SizeJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.hexworks.zircon.api.data.Size

class SizeJsonAdapter {

	@ToJson
	fun toJson(obj: Size) = obj.deserialize()

	@FromJson
	fun fromJson(json: SizeJson) = json.serialize()
}

fun Size.deserialize() = SizeJson(width, height)