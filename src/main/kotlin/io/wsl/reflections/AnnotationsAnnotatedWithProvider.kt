package io.wsl.reflections

interface AnnotationsAnnotatedWithProvider {
    fun provide(annotations: Array<Annotation>, annotationClass: Class<out Annotation>): List<Annotation>
}