package io.wsl.handlers

import io.wsl.extensions.ExtensionModel
import io.wsl.extensions.ExtensionsFromClassProvider
import org.springframework.stereotype.Component

@Component
class DefaultHandlerProvider(var allowedOriginsProvider: HandlerAllowedOriginsProvider, var pathProvider: HandlerPathProvider, var extensionsFromClassProvider: ExtensionsFromClassProvider, var handlerGenerator: HandlerGenerator, var handlerIsDefaultChecker: HandlerClassIsDefaultHandlerChecker) : HandlerProvider {
    override fun provide(clazz: Class<*>, handler: io.wsl.Handler): Handler {
        val globalConfigExtensions = mutableListOf<ExtensionModel>() // TODO: There's no global config extensions.
        val isDefault = handlerIsDefaultChecker.check(clazz)
        val extensions = extensionsFromClassProvider.provide(clazz, globalConfigExtensions)
        val path = pathProvider.provide(handler)
        val allowedOrigins = allowedOriginsProvider.provide(handler)
        return handlerGenerator.generate(path, allowedOrigins, isDefault, clazz, extensions)
    }
}