package io.wsl.handlers

import org.springframework.stereotype.Component

@Component
class HandlerIsDefaultCheckerImpl : HandlerIsDefaultChecker {
    override fun check(handler: Handler): Boolean {
        return handler.isDefault
    }
}