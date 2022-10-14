package io.wsl.events

interface EventHandlerListProvider {
    fun provide(includedClass: List<Class<*>>): List<BaseEventHandler>
}