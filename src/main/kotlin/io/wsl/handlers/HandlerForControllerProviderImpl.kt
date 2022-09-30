package io.wsl.handlers

import io.wsl.exceptions.DefaultHandlerNotSet
import io.wsl.exceptions.HandlerNotResolved
import org.springframework.stereotype.Component

@Component
class HandlerForControllerProviderImpl(var handlerByClassProvider: HandlerByClassProvider) : HandlerForControllerProvider {
    override fun provide(controllerClass: Class<*>, setHandler: Class<*>?, handlers: List<Handler>, defaultHandler: Handler?): Handler {
        return if (setHandler != null) {
            handlerByClassProvider.provide(handlers, setHandler) ?: throw HandlerNotResolved()
        } else {
            defaultHandler ?: throw DefaultHandlerNotSet()
        }
    }
}