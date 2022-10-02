package io.wsl.parameters

import io.wsl.extensions.ExtensionModelFromParameterProvider
import org.springframework.stereotype.Component
import java.lang.reflect.Parameter

@Component
class DefaultActionParameterProvider(var extensionModelFromParameterProvider: ExtensionModelFromParameterProvider) : ActionParameterProvider {
    override fun provide(parameter: Parameter): ActionParameter {
        val extensionModel = extensionModelFromParameterProvider.provide(parameter)
        return ActionParameter(parameter.type, extensionModel)
    }
}