package com.riga.bungeon.resources.json.adapter

import com.riga.bungeon.resources.json.models.DebugConfigJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.hexworks.zircon.api.application.DebugConfig

class DebugConfigJsonAdapter {

	@ToJson
	fun toJson(obj: DebugConfig) = obj.deserialize()

	@FromJson
	fun fromJson(json: DebugConfigJson) = json.serialize()
}

fun DebugConfig.deserialize() = DebugConfigJson(displayGrid, displayCoordinates, displayFps)