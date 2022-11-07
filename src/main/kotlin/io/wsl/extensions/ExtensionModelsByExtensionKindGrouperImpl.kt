package io.wsl.extensions

import org.springframework.stereotype.Component

@Component
class ExtensionModelsByExtensionKindGrouperImpl : ExtensionModelsByExtensionKindGrouper {
    override fun group(extensions: List<ExtensionModel>): Map<ExtensionKind, List<ExtensionModel>> {
        return extensions.groupBy { it.extensionKind }
    }
}