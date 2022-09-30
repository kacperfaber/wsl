package io.wsl.handlers

import org.springframework.stereotype.Component

@Component
class DefaultHandlerInstanceComparer : HandlerInstanceComparer {
    override fun compare(handler1: Handler, handler2: Handler): Boolean {
        return handler1 == handler2
    }
}