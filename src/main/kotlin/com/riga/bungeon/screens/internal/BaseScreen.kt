package com.riga.bungeon.screens.internal

import com.riga.bungeon.core.data.GameConfig
import com.riga.bungeon.system.SoundSystem
import com.riga.bungeon.screens.FatalErrorScreen
import com.riga.bungeon.screens.internal.interfaces.ComponentsExtended
import com.riga.bungeon.screens.internal.interfaces.UIEventsExtended
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.system.exitProcess

abstract class BaseScreen(
	private val tileGrid: TileGrid,
	protected val gameConfig: GameConfig,
	protected val bundle: Bundle?
) : BaseView(tileGrid, gameConfig.colorTheme), UIEventsExtended, ComponentsExtended {

	protected fun <T : BaseScreen> navigateTo(next: KClass<T>, bundle: Bundle? = null) {
		next.primaryConstructor?.call(tileGrid, gameConfig, bundle)?.let {
			replaceWith(it)
		}
	}
	
	protected fun showFatalErrorScreen(reason: String) {
		Bundle().apply {
			put("message", reason)
		}.also {
			navigateTo(FatalErrorScreen::class, it)
		}
	}

	override fun onUndock() {
		super.onUndock()
		SoundSystem.stopBackGroundMusic()
	}
	
	protected fun exitGame(): Nothing = exitProcess(0)
}