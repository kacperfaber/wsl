package io.wsl

class ListBuilder<T> internal constructor() {
    var list = mutableListOf<T>()
        private set

    fun yield(t: T) = list.add(t)

    fun yieldAll(vararg items: T) = list.addAll(items)
}

internal fun <T> listBuilder(action: ListBuilder<T>.() -> Unit): MutableList<T> {
    val listBuilder = ListBuilder<T>()
    action(listBuilder)
    return listBuilder.list
}