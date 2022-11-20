package io.wsl.handlers

interface HandlerPathProvider {
    fun provide(handler: io.wsl.Handler): String
}