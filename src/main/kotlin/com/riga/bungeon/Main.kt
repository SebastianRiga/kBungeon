package com.riga.bungeon

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.screen.Screen

fun main(args: Array<String>) {
	val tileGrid = SwingApplications.startTileGrid()
	val screen = Screen.create(tileGrid)

	val header = Components.header()
		.withText("Hello Bungeon!")
		.withAlignmentWithin(screen, ComponentAlignment.CENTER)

	screen.addComponent(header)
	screen.theme = ColorThemes.arc()
	screen.display()
}