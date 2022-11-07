package io.wsl.executor

import io.wsl.extensions.ExtensionModel

interface InvokeParameterProvider {
    fun provide(parameterType: Class<*>, value: Any?, extensions: List<ExtensionModel>): InvokeParameter
}