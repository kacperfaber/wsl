package io.wsl.extensions

interface ActionParameterKindExtensionModelsProvider {
    fun provide(extensions: List<ExtensionModel>): List<ExtensionModel>
}