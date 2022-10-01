package io.wsl.controllers

import io.wsl.SocketController
import io.wsl.extensions.ExtensionsFromClassProvider
import io.wsl.handlers.Handler
import io.wsl.handlers.HandlerForControllerProvider
import org.springframework.stereotype.Component

@Component
class DefaultControllerProvider(var extensionsFromClassProvider: ExtensionsFromClassProvider, var setHandlerValueProvider: SetHandlerValueProvider, var handlerForControllerProvider: HandlerForControllerProvider) : ControllerProvider {
    override fun provide(clazz: Class<*>, socketController: SocketController, handlers: List<Handler>, defaultHandler: Handler?): Controller {
        val setHandlerClass = setHandlerValueProvider.provide(clazz)
        val handler = handlerForControllerProvider.provide(clazz, setHandlerClass, handlers, defaultHandler)
        val extensions = extensionsFromClassProvider.provide(clazz)

        return Controller(clazz, handler, extensions)
    }
}