package io.wsl.events

interface BaseEventHandlersByKeyProvider {
    fun provide(key: Class<*>): List<BaseEventHandler>
}