package io.wsl

class ListBuilder<T> internal constructor(initialList: MutableList<T>? = null) {
    var list = initialList ?: mutableListOf()
        private set

    fun yield(t: T) = list.add(t)

    fun yieldAll(vararg items: T) = list.addAll(items)
}

internal fun <T> listBuilder(action: ListBuilder<T>.() -> Unit): MutableList<T> {
    val listBuilder = ListBuilder<T>()
    action(listBuilder)
    return listBuilder.list
}

internal fun <T> listBuilderFrom(list: MutableList<T>, action: ListBuilder<T>.() -> Unit): MutableList<T> {
    val listBuilder = ListBuilder(list)
    action(listBuilder)
    return listBuilder.list
}