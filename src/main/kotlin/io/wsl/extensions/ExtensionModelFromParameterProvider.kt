package io.wsl.extensions

import java.lang.reflect.Parameter

interface ExtensionModelFromParameterProvider {
    fun provide(parameter: Parameter): ExtensionModel?
}