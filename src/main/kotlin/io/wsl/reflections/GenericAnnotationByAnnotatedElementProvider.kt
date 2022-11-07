package io.wsl.reflections

import java.lang.reflect.AnnotatedElement

interface GenericAnnotationByAnnotatedElementProvider {
    fun <T : Annotation> provide(annotatedElement: AnnotatedElement, annotationClass: Class<out T>): T?
}