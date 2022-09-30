package io.wsl.controllers

interface SetHandlerValueProvider {
    fun provide(controllerClass: Class<*>): Class<*>?
}