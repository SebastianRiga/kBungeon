package com.riga.bungeon.exceptions

data class ResourceNotFoundException(override val message: String) : Exception(message)