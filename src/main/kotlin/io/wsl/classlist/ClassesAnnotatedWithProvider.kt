package io.wsl.classlist

interface ClassesAnnotatedWithProvider {
    fun provide(classList: ClassList, annotationClass: Class<out Annotation>): List<Class<*>>
}