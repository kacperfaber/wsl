package io.wsl.reflections

import java.lang.reflect.Method

interface MethodGenericAnnotationProvider {
    fun <T : Annotation> provide(method: Method, annotationClass: Class<T>): T?
}