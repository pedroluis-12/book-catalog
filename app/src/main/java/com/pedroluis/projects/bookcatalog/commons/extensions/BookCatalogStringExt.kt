package com.pedroluis.projects.bookcatalog.commons.extensions

const val STRING_EMPTY = ""

fun List<String>.setupListToString(
    initSignal: String = STRING_EMPTY, separator: String? = null
) = if (this.isNotEmpty()) {
    var aux = STRING_EMPTY
    this.forEachIndexed { index, item ->
        aux += "$initSignal$item"
        if (index.inc() != this.size) {
            aux += separator
        }
    }
    aux
} else {
    STRING_EMPTY
}
