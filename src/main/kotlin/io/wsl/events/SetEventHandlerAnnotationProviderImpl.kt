package io.wsl.events

import io.wsl.exceptions.InvalidEventHandlerMethodException
import io.wsl.reflections.AnnotationClassProvider
import io.wsl.reflections.AnnotationsAnnotatedWithProvider
import io.wsl.reflections.GenericAnnotationProvider
import org.springframework.stereotype.Component

@Component
class SetEventHandlerAnnotationProviderImpl(var annotationClassProvider: AnnotationClassProvider, var genericAnnotationProvider: GenericAnnotationProvider, var annotationsAnnotatedWithProvider: AnnotationsAnnotatedWithProvider) : SetEventHandlerAnnotationProvider {
    override fun provide(annotations: Array<Annotation>): SetEventHandler? {
        val annotatedWith = annotationsAnnotatedWithProvider.provide(annotations, SetEventHandler::class.java)

        if (annotatedWith.count() > 1) {
            throw InvalidEventHandlerMethodException()
        }

        val annotation = annotatedWith.firstOrNull() ?: return null
        return genericAnnotationProvider.provide(annotationClassProvider.provide(annotation), SetEventHandler::class.java)
    }
}