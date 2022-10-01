package io.wsl.handlers

import io.wsl.reflections.ClassComparer
import org.springframework.stereotype.Component

@Component
class DefaultHandlerByClassProvider(var classComparer: ClassComparer) : HandlerByClassProvider {
    override fun provide(handlers: List<Handler>, handlerClass: Class<*>): Handler? {
        return handlers.firstOrNull { classComparer.compare(it.clazz, handlerClass) }
    }
}