package com.riga.bungeon.exceptions

data class AttributeDoesNotExistsException(override val message: String): NoSuchElementException(message) 