package io.wsl.extensions

interface ExtensionModelGenerator {
    fun generate(componentClass: Class<out ExtensionComponent>, annotation: Annotation, extensionKind: ExtensionKind): ExtensionModel
}