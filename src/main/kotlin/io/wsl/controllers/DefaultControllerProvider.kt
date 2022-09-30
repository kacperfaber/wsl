package io.wsl.controllers

import io.wsl.ArrayToListConverter
import io.wsl.SocketController
import io.wsl.extensions.ExtensionModelsProvider
import io.wsl.handlers.Handler
import io.wsl.handlers.HandlerForControllerProvider
import io.wsl.reflections.ClassAnnotationsProvider
import org.springframework.stereotype.Component

@Component
class DefaultControllerProvider(var arrayToListConverter: ArrayToListConverter, var annotationsProvider: ClassAnnotationsProvider, var extensionModelsProvider: ExtensionModelsProvider, var setHandlerValueProvider: SetHandlerValueProvider, var handlerForControllerProvider: HandlerForControllerProvider) : ControllerProvider {
    override fun provide(clazz: Class<*>, socketController: SocketController, handlers: List<Handler>, defaultHandler: Handler?): Controller {
        val setHandlerClass = setHandlerValueProvider.provide(clazz)
        val handler = handlerForControllerProvider.provide(clazz, setHandlerClass, handlers, defaultHandler)

        // TODO: Replace with functionality to do this two line automatically
        val allAnnotations = arrayToListConverter.convert(annotationsProvider.provide(clazz))
        val extensions = extensionModelsProvider.provide(allAnnotations)

        return Controller(clazz, handler, extensions)
    }
}