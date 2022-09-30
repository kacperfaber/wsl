package io.wsl.handlers

import org.springframework.stereotype.Component

@Component
class DefaultHandlerProvider : HandlerProvider {
    override fun provide(clazz: Class<*>, handler: io.wsl.Handler): Handler {
        TODO("Not yet implemented")
    }
}