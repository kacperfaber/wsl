package io.wsl.handlers

import io.wsl.DefaultHandler
import io.wsl.reflections.GenericAnnotationProvider
import io.wsl.reflections.IsAnnotationPresentChecker
import org.springframework.stereotype.Component

@Component
class DefaultHandlerClassIsDefaultHandlerChecker(var isAnnotationPresentChecker: IsAnnotationPresentChecker) : HandlerClassIsDefaultHandlerChecker {
    override fun check(clazz: Class<*>): Boolean {
        return isAnnotationPresentChecker.isAnnotationPresent(clazz, DefaultHandler::class.java)
    }
}