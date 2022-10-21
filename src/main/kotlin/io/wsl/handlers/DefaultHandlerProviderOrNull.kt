package io.wsl.handlers

interface DefaultHandlerProviderOrNull {
    fun provide(handlers: List<Handler>): Handler?
}