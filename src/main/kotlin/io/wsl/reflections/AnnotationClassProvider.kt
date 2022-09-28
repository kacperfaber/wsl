package io.wsl.reflections

interface AnnotationClassProvider {
    fun provide(annotation: Annotation): Class<out Annotation>
}