package io.wsl.extensions

import io.wsl.ArrayToListConverter
import io.wsl.reflections.ClassAnnotationsProvider
import org.springframework.stereotype.Component

@Component
class DefaultExtensionsFromClassProvider(var arrayToListConverter: ArrayToListConverter, var classAnnotationsProvider: ClassAnnotationsProvider, var extensionModelsProvider: ExtensionModelsProvider) : ExtensionsFromClassProvider {
    override fun provide(clazz: Class<*>): List<ExtensionModel> {
        val annotations = classAnnotationsProvider.provide(clazz)
        val annotationList = arrayToListConverter.convert(annotations)
        return extensionModelsProvider.provide(annotationList)
    }
}