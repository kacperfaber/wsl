package io.wsl.extensions

import io.wsl.exceptions.InvalidActionParameter
import org.springframework.stereotype.Component
import java.lang.reflect.Parameter

@Component
class DefaultExtensionModelFromParameterProvider(var extensionModelsFromParameterProvider: ExtensionModelsFromParameterProvider) : ExtensionModelFromParameterProvider {
    override fun provide(parameter: Parameter): ExtensionModel? {
        val extensions = extensionModelsFromParameterProvider.provide(parameter)
        if (extensions.count() > 1) {
            throw InvalidActionParameter()
        }
        return extensions.firstOrNull()
    }
}