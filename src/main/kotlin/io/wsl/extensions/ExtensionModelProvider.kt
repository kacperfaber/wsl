package io.wsl.extensions

interface ExtensionModelProvider {
    fun provide(annotation: Annotation, extensionComponentClass:Class<out ExtensionComponent>): ExtensionModel
}