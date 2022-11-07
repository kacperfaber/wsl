package io.wsl.extensions

import org.springframework.stereotype.Component

@Component
class ActionParameterKindExtensionModelsProviderImpl : ActionParameterKindExtensionModelsProvider {
    override fun provide(extensions: List<ExtensionModel>): List<ExtensionModel> {
        return extensions.filter { it.extensionKind == ExtensionKind.ActionParameter }
    }
}