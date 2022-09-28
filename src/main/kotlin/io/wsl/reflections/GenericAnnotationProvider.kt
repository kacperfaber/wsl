package io.wsl.reflections

interface GenericAnnotationProvider {
    fun <T : Annotation> provide(clazz: Class<*>, annotationClass: Class<T>): T?
}