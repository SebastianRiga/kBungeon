package com.riga.bungeon.resources.json.models

import org.hexworks.zircon.api.application.DebugConfig

data class DebugConfigJson(
	val displayGrid: Boolean,
	val displayCoordinates: Boolean,
	val displayFps: Boolean
) {
	fun serialize() = DebugConfig(displayGrid, displayCoordinates, displayFps)
}