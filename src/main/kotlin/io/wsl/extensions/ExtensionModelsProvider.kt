package io.wsl.extensions

interface ExtensionModelsProvider {
    fun provide(annotations: List<Annotation>): List<ExtensionModel>
    fun provide(annotations: List<Annotation>, initialList: MutableList<ExtensionModel>): MutableList<ExtensionModel>
}