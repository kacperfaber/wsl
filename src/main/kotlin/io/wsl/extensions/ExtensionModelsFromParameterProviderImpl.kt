package io.wsl.extensions

import io.wsl.ArrayToListConverter
import io.wsl.reflections.ParameterAnnotationsProvider
import org.springframework.stereotype.Component
import java.lang.reflect.Parameter

@Component
class ExtensionModelsFromParameterProviderImpl(var arrayToListConverter: ArrayToListConverter, var extensionModelsProvider: ExtensionModelsProvider, var annotationsProvider: ParameterAnnotationsProvider) : ExtensionModelsFromParameterProvider {
    override fun provide(parameter: Parameter): List<ExtensionModel> {
        val annotationArray = annotationsProvider.provide(parameter)
        val annotationList = arrayToListConverter.convert(annotationArray)
        return extensionModelsProvider.provide(annotationList)
    }
}