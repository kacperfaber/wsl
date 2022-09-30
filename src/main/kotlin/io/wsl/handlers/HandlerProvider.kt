package io.wsl.handlers

interface HandlerProvider {
    fun provide(clazz: Class<*>, handler: io.wsl.Handler): Handler
}