package io.wsl.extensions

import java.lang.reflect.Parameter

interface ExtensionModelsFromParameterProvider {
    fun provide(parameter: Parameter): List<ExtensionModel>
}