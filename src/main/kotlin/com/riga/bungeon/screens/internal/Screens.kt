package com.riga.bungeon.screens.internal

import com.riga.bungeon.screens.FatalErrorScreen

object Screens {
	fun showFatalErrorScreen(reason: String) = Bundle().run {
		put("message", reason)
		FatalErrorScreen(this).dock()
	}
}