package io.wsl.reflections

interface AnnotationIsAnnotatedChecker {
    fun hasAnnotation(annotation: Annotation, targetAnnotationClass: Class<out Annotation>): Boolean
}