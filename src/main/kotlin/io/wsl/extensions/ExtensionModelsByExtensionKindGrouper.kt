package io.wsl.extensions

interface ExtensionModelsByExtensionKindGrouper {
    fun group(extensions: List<ExtensionModel>): Map<ExtensionKind, List<ExtensionModel>>
}