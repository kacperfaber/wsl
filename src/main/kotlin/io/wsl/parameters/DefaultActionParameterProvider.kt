package io.wsl.parameters

import io.wsl.extensions.ExtensionModelsFromParameterProvider
import org.springframework.stereotype.Component
import java.lang.reflect.Parameter

@Component
class DefaultActionParameterProvider(var extensionsProvider: ExtensionModelsFromParameterProvider) : ActionParameterProvider {
    override fun provide(parameter: Parameter): ActionParameter {
        val extensions = extensionsProvider.provide(parameter)
        return ActionParameter(parameter.type, /* This value not going to be used */null, extensions)
    }
}