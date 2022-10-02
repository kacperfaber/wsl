package io.wsl.extensions

import io.wsl.ArrayToListConverter
import io.wsl.exceptions.InvalidActionParameter
import io.wsl.reflections.ParameterAnnotationsProvider
import org.springframework.stereotype.Component
import java.lang.reflect.Parameter

@Component
class DefaultExtensionModelFromParameterProvider(var arrayToListConverter: ArrayToListConverter, var extensionModelsProvider: ExtensionModelsProvider, var annotationsProvider: ParameterAnnotationsProvider) : ExtensionModelFromParameterProvider {
    override fun provide(parameter: Parameter): ExtensionModel? {
        val annotationArray = annotationsProvider.provide(parameter)
        val annotationList = arrayToListConverter.convert(annotationArray)
        val extensions = extensionModelsProvider.provide(annotationList)
        if (extensions.count() > 1) {
            throw InvalidActionParameter()
        }
        return extensions.firstOrNull()
    }
}