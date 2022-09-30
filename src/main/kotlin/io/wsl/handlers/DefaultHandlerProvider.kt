package io.wsl.handlers

import io.wsl.ArrayToListConverter
import io.wsl.extensions.ExtensionModelsProvider
import io.wsl.reflections.ClassAnnotationsProvider
import org.springframework.stereotype.Component

@Component
class DefaultHandlerProvider(var arrayToListConverter: ArrayToListConverter, var classAnnotationsProvider: ClassAnnotationsProvider, var handlerGenerator: HandlerGenerator, var extensionModelsProvider: ExtensionModelsProvider, var handlerIsDefaultChecker: HandlerClassIsDefaultHandlerChecker) : HandlerProvider {
    override fun provide(clazz: Class<*>, handler: io.wsl.Handler): Handler {
        val isDefault = handlerIsDefaultChecker.check(clazz)
        val allAnnotations = classAnnotationsProvider.provide(clazz)
        val allAnnotationsList = arrayToListConverter.convert(allAnnotations)
        val extensions = extensionModelsProvider.provide(allAnnotationsList)

        // TODO: Replace handler.path and handler.allowedOrigins with the dependencies to provide that data.

        return handlerGenerator.generate(handler.path, handler.allowedOrigins, isDefault, clazz, extensions)
    }
}