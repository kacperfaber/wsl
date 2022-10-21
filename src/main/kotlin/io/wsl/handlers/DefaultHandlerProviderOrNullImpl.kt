package io.wsl.handlers

import io.wsl.exceptions.MoreThanOneDefaultHandlerSet
import org.springframework.stereotype.Component

@Component
class DefaultHandlerProviderOrNullImpl(var allDefaultHandlersProvider: AllDefaultHandlersProvider) : DefaultHandlerProviderOrNull {
    override fun provide(handlers: List<Handler>): Handler? {
        val defaultHandlers = allDefaultHandlersProvider.provide(handlers)
        if (defaultHandlers.count() > 1) throw MoreThanOneDefaultHandlerSet(defaultHandlers)
        return defaultHandlers.firstOrNull()
    }
}