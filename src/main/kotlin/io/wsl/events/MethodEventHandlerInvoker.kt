package io.wsl.events

interface MethodEventHandlerInvoker {
    fun invoke(eventHandler: MethodEventHandler, parameterInstance: Any)
}