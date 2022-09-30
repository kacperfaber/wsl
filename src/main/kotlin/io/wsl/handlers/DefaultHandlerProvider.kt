package io.wsl.handlers

import io.wsl.extensions.ExtensionModelsProvider
import io.wsl.reflections.ClassAnnotationsProvider
import org.springframework.stereotype.Component

@Component
class DefaultHandlerProvider(var classAnnotationsProvider: ClassAnnotationsProvider, var handlerGenerator: HandlerGenerator, var extensionModelsProvider: ExtensionModelsProvider, var handlerIsDefaultChecker: HandlerClassIsDefaultHandlerChecker) : HandlerProvider {
    override fun provide(clazz: Class<*>, handler: io.wsl.Handler): Handler {
        val isDefault = handlerIsDefaultChecker.check(clazz)
        val allAnnotations = classAnnotationsProvider.provide(clazz)
        val extensions = extensionModelsProvider.provide(allAnnotations.toList()) // TODO: replace .toList() with dedicated system

        // TODO: Replace handler.path and handler.allowedOrigins with the dependencies to provide that data.

        return handlerGenerator.generate(handler.path, handler.allowedOrigins, isDefault, clazz, extensions)
    }
}