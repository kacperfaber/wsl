package io.wsl.invoke_parameter

import io.wsl.extensions.ExtensionModel

interface InvokeParameterProvider {
    fun provide(parameterType: Class<*>, value: Any?, extensions: List<ExtensionModel>): InvokeParameter
}