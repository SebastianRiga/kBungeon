package com.riga.bungeon.meta.extensions

import com.riga.bungeon.meta.classes.WeakIdentityHashMap

fun <T, K> mutableWeakIdentityHashMapOf() = WeakIdentityHashMap<T, K>()