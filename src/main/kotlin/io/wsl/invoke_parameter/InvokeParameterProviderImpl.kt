package io.wsl.invoke_parameter

import io.wsl.extensions.ExtensionModel
import org.springframework.stereotype.Component

@Component
class InvokeParameterProviderImpl : InvokeParameterProvider {
    override fun provide(parameterType: Class<*>, value: Any?, extensions: List<ExtensionModel>): InvokeParameter {
        return InvokeParameter(parameterType, value, extensions)
    }
}