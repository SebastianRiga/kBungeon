package com.riga.bungeon

import GlobalLogger
import com.riga.bungeon.resources.ResourceHandler
import guard
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.screen.Screen
import kotlin.system.exitProcess

fun main(args: Array<String>) {
	guard({ ResourceHandler.loadAppConfig() }, ::onLoadAppConfigError) { appConfig ->
		val tileGrid = SwingApplications.startTileGrid(appConfig)
		val screen = Screen.create(tileGrid)

		val header = Components.header()
			.withText("Hello Bungeon!")
			.withAlignmentWithin(screen, ComponentAlignment.CENTER)

		screen.addComponent(header)
		screen.theme = ColorThemes.arc()
		screen.display()
	}
}

fun onLoadAppConfigError(e: Exception) {
	GlobalLogger.catching(e)
	exitProcess(1)
}