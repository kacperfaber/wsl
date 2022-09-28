package io.wsl.extensions

interface SetComponentAnnotationProvider {
    fun provide(clazz: Class<out Annotation>): SetComponent?
}