package io.wsl.handlers

interface HandlerForControllerProvider {
    fun provide(controllerClass: Class<*>, setHandler: Class<*>?, handlers: List<Handler>, defaultHandler: Handler?): Handler
}