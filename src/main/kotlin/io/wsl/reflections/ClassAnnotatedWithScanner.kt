package io.wsl.reflections

interface ClassAnnotatedWithScanner {
    fun scan(packagePrefix: String, annotationClass: Class<out Annotation>): Set<Class<*>>
}