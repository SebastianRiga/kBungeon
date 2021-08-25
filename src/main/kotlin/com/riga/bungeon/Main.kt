package com.riga.bungeon

import com.riga.bungeon.core.data.builders.GConfigBuilder
import com.riga.bungeon.meta.extensions.GlobalLogger
import com.riga.bungeon.screens.TitleScreen
import org.hexworks.zircon.api.SwingApplications

/**
 * Main entry point
 */
fun main(args: Array<String>) {
	GlobalLogger.info(args.joinToString(", "))

	GConfigBuilder.newDefault().run {
		SwingApplications.startTileGrid(toAppConfig()).apply {
			TitleScreen(this, this@run).dock()
		}
	}
}
