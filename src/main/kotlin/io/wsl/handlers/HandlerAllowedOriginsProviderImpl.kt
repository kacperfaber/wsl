package io.wsl.handlers

import io.wsl.Handler
import org.springframework.stereotype.Component

@Component
class HandlerAllowedOriginsProviderImpl : HandlerAllowedOriginsProvider {
    override fun provide(handler: Handler): Array<String> {
        return handler.allowedOrigins
    }
}