package io.wsl.handlers

interface HandlerByClassProvider {
    fun provide(handlers: List<Handler>, handlerClass: Class<*>): Handler?
}