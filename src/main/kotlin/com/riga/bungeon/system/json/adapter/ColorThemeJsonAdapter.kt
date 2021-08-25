package com.riga.bungeon.system.json.adapter

import com.riga.bungeon.system.json.models.ColorThemeJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.hexworks.zircon.api.component.ColorTheme

class ColorThemeJsonAdapter {

	@FromJson
	fun fromJson(json: ColorThemeJson): ColorTheme = json.serialize()

	@ToJson
	fun toJson(obj: ColorTheme) = obj.deserialize()
}

fun ColorTheme.deserialize() = ColorThemeJson(
	primaryForegroundColor.deserialize(),
	secondaryForegroundColor.deserialize(),
	primaryBackgroundColor.deserialize(),
	secondaryBackgroundColor.deserialize(),
	accentColor.deserialize()
)