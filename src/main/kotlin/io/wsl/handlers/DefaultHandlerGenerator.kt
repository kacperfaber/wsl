package io.wsl.handlers

import org.springframework.stereotype.Component

@Component
class DefaultHandlerGenerator : HandlerGenerator {
    override fun generate(path: String, allowedOrigins: Array<String>, isDefault: Boolean): Handler {
        return Handler(path, allowedOrigins, isDefault)
    }
}