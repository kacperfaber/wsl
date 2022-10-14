package io.wsl.reflections

interface AnnotationsFromAnnotationProvider {
    fun <T> provide(annotation: Annotation, annotationClass: Class<T>): T? where T : Annotation
}