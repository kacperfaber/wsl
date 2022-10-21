package io.wsl.handlers

import org.springframework.stereotype.Component

@Component
class AllDefaultHandlersProviderImpl(var isDefaultChecker: HandlerIsDefaultChecker) : AllDefaultHandlersProvider {
    override fun provide(handlers: List<Handler>): List<Handler> {
        return handlers.filter { isDefaultChecker.check(it) }
    }
}