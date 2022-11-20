package io.wsl.handlers

import org.springframework.stereotype.Component

@Component
class HandlerPathProviderImpl : HandlerPathProvider {
    override fun provide(handler: io.wsl.Handler): String = handler.path
}