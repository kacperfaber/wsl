package io.wsl.handlers

import org.springframework.stereotype.Component

@Component
class DefaultHandlerByClassProvider(var handlerComparer: HandlerInstanceComparer) : HandlerByClassProvider {
    override fun provide(handlers: List<Handler>, handlerClass: Class<*>): Handler? {
        return handlers.firstOrNull { handlerClass == it.clazz } // TODO: Replace it with dependency.
    }
}