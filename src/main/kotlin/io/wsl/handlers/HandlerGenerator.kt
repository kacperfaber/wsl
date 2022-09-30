package io.wsl.handlers

import io.wsl.extensions.ExtensionModel

interface HandlerGenerator {
    fun generate(
        path: String,
        allowedOrigins: Array<String>,
        isDefault: Boolean,
        clazz: Class<*>,
        extensions: List<ExtensionModel>
    ): Handler
}