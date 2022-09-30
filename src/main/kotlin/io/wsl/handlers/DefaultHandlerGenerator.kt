package io.wsl.handlers

import io.wsl.extensions.ExtensionModel
import org.springframework.stereotype.Component

@Component
class DefaultHandlerGenerator : HandlerGenerator {
    override fun generate(
        path: String,
        allowedOrigins: Array<String>,
        isDefault: Boolean,
        clazz: Class<*>,
        extensions: List<ExtensionModel>
    ): Handler {
        return Handler(path, allowedOrigins, isDefault, clazz, extensions)
    }
}