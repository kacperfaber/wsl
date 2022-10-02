package io.wsl.handlers

import io.wsl.extensions.ExtensionModel

open class Handler(
    var path: String,
    var allowedOrigins: Array<String>,
    var isDefault: Boolean,
    var clazz: Class<*>,
    var extensions: MutableList<ExtensionModel>
)