package com.riga.bungeon.exceptions

data class ResourceParsingException(override val message: String) : Exception(message)