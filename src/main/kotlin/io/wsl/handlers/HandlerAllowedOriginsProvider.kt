package io.wsl.handlers

interface HandlerAllowedOriginsProvider {
    fun provide(handler: io.wsl.Handler): Array<String>
}