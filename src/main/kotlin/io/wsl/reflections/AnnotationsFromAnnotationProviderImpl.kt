package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class AnnotationsFromAnnotationProviderImpl(var annotationClassProvider: AnnotationClassProvider, var genericAnnotationProvider: GenericAnnotationProvider) : AnnotationsFromAnnotationProvider {
    override fun <T : Annotation> provide(annotation: Annotation, annotationClass: Class<T>): T? {
        return genericAnnotationProvider.provide(annotationClassProvider.provide(annotation), annotationClass)
    }
}