package io.wsl.extensions

import io.wsl.listBuilder
import io.wsl.reflections.AnnotationClassProvider
import org.springframework.stereotype.Component

@Component
class DefaultExtensionModelsProvider(var setComponentClassProvider: ExtensionComponentClassProvider, var extensionModelProvider: ExtensionModelProvider, var annotationClassProvider: AnnotationClassProvider, var setComponentProvider: SetComponentAnnotationProvider) : ExtensionModelsProvider {
    override fun provide(annotations: List<Annotation>): List<ExtensionModel>  = listBuilder {
        for (annotation in annotations) {
            val annotationClass = annotationClassProvider.provide(annotation)
            val setComponent = setComponentProvider.provide(annotationClass) ?: continue
            val componentClass = setComponentClassProvider.provide(setComponent)
            val model = extensionModelProvider.provide(annotation, componentClass)
            yield(model)
        }
    }
}