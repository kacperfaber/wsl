package io.wsl.extensions

interface ExtensionModelsProvider {
    fun provide(annotations: List<Annotation>): List<ExtensionModel>
}