package io.wsl.handlers

import io.wsl.extensions.ExtensionModel
import io.wsl.extensions.ExtensionsFromClassProvider
import org.springframework.stereotype.Component

@Component
class DefaultHandlerProvider(var extensionsFromClassProvider: ExtensionsFromClassProvider, var handlerGenerator: HandlerGenerator, var handlerIsDefaultChecker: HandlerClassIsDefaultHandlerChecker) : HandlerProvider {
    override fun provide(clazz: Class<*>, handler: io.wsl.Handler): Handler {
        val globalConfigExtensions = mutableListOf<ExtensionModel>() // TODO: There's no global config extensions.
        val isDefault = handlerIsDefaultChecker.check(clazz)
        val extensions = extensionsFromClassProvider.provide(clazz, globalConfigExtensions)

        // TODO: Replace handler.path and handler.allowedOrigins with the dependencies to provide that data.
        return handlerGenerator.generate(handler.path, handler.allowedOrigins, isDefault, clazz, extensions)
    }
}