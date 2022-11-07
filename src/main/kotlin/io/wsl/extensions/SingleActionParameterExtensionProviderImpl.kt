package io.wsl.extensions

import io.wsl.exceptions.MoreThanOneActionParameterExtension
import org.springframework.stereotype.Component

@Component
class SingleActionParameterExtensionProviderImpl : SingleActionParameterExtensionProvider {
    override fun provide(extensions: List<ExtensionModel>): ExtensionModel? {
        val res = extensions.filter { it.extensionKind == ExtensionKind.ActionParameter }
        if (res.size > 1) {
            throw MoreThanOneActionParameterExtension()
        }
        return res.firstOrNull()
    }
}