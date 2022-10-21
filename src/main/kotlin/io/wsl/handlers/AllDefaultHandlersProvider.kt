package io.wsl.handlers

interface AllDefaultHandlersProvider {
    fun provide(handlers: List<Handler>): List<Handler>
}