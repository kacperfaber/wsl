package io.wsl.handlers

import io.wsl.DefaultHandler
import io.wsl.reflections.GenericAnnotationProvider
import org.springframework.stereotype.Component

@Component
class DefaultHandlerClassIsDefaultHandlerChecker(var genericAnnotationProvider: GenericAnnotationProvider) : HandlerClassIsDefaultHandlerChecker {
    override fun check(clazz: Class<*>): Boolean {
        // TODO: Can be replaced with 'IsAnnotationPresentChecker' [io.wsl.reflections]

        return genericAnnotationProvider.provide(clazz, DefaultHandler::class.java) != null
    }
}