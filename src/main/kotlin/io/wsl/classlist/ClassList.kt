package io.wsl.classlist

class ClassList(var set: Set<Class<*>>) : Iterable<Class<*>> {
    constructor() : this(setOf())

    override fun iterator(): Iterator<Class<*>> {
        return set.iterator()
    }
}