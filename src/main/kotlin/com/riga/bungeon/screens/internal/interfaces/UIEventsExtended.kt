package com.riga.bungeon.screens.internal.interfaces

import com.riga.bungeon.meta.extensions.schedule
import com.riga.bungeon.system.SoundSystem
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.zircon.api.component.Button
import org.hexworks.zircon.api.uievent.MouseEventType

interface UIEventsExtended {
	fun Button.onClick(handler: () -> Unit): Subscription {
		return this.processMouseEvents(MouseEventType.MOUSE_CLICKED) { _, _ ->
			SoundSystem.playSoundEffect("click")
			handler()
		}
	}
	
	fun Button.onClickDelay(delay: Long, handler: () -> Unit): Subscription {
		return processMouseEvents(MouseEventType.MOUSE_CLICKED) { _, _ ->
			SoundSystem.playSoundEffect("click")
			schedule(delay, "Playing button sound effect") {
				handler()
			}
		}
	}
}