package io.wsl.controllers

import io.wsl.SetHandler
import io.wsl.reflections.GenericAnnotationProvider
import org.springframework.stereotype.Component

@Component
class DefaultSetHandlerAnnotationProvider(var genericAnnotationProvider: GenericAnnotationProvider) : SetHandlerAnnotationProvider {
    override fun provide(clazz: Class<*>): SetHandler? {
        return genericAnnotationProvider.provide(clazz, SetHandler::class.java)
    }
}