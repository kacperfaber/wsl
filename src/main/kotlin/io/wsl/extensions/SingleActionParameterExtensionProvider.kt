package io.wsl.extensions

interface SingleActionParameterExtensionProvider {
    fun provide(extensions: List<ExtensionModel>): ExtensionModel?
}