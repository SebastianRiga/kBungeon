package com.riga.bungeon.meta.classes

import com.riga.bungeon.meta.extensions.mutableWeakIdentityHashMapOf
import kotlin.reflect.KProperty

class BackingField<R, T : Any>(private val init: (R) -> T = { throw IllegalStateException("Property not initialized!") }) {
	private val store = mutableWeakIdentityHashMapOf<R, T>()

	operator fun getValue(self: R, prop: KProperty<*>): T = store[self] ?: setValue(self, prop, init(self))

	operator fun setValue(self: R, prop: KProperty<*>, value: T): T = value.apply {
		store[self] = this
	}
}