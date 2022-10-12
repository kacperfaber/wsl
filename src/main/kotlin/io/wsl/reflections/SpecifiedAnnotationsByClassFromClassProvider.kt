package io.wsl.reflections

interface SpecifiedAnnotationsByClassFromClassProvider {
    fun <T> provide(clazz: Class<*>, annotationClass: Class<T>): List<T> where T : Annotation
}