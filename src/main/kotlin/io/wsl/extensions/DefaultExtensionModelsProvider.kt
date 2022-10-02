package io.wsl.extensions

import io.wsl.ListBuilder
import io.wsl.listBuilder
import io.wsl.listBuilderFrom
import io.wsl.reflections.AnnotationClassProvider
import org.springframework.stereotype.Component

@Component
class DefaultExtensionModelsProvider(var setComponentClassProvider: ExtensionComponentClassProvider, var extensionModelProvider: ExtensionModelProvider, var annotationClassProvider: AnnotationClassProvider, var setComponentProvider: SetComponentAnnotationProvider) : ExtensionModelsProvider {
    override fun provide(annotations: List<Annotation>): List<ExtensionModel>  = listBuilder {
        provide(annotations, this)
    }

    override fun provide(annotations: List<Annotation>, initialList: MutableList<ExtensionModel>): MutableList<ExtensionModel> {
        return listBuilderFrom(initialList) {
            provide(annotations, this)
        }
    }

    internal fun provide(annotations: List<Annotation>, listBuilder: ListBuilder<ExtensionModel>) {
        for (annotation in annotations) {
            val annotationClass = annotationClassProvider.provide(annotation)
            val setComponent = setComponentProvider.provide(annotationClass) ?: continue
            val componentClass = setComponentClassProvider.provide(setComponent)
            val model = extensionModelProvider.provide(annotation, componentClass)
            listBuilder.yield(model)
        }
    }
}