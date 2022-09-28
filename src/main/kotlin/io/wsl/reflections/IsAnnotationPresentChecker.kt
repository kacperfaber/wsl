package io.wsl.reflections

interface IsAnnotationPresentChecker {
    fun isAnnotationPresent(clazz: Class<*>, annotationClass: Class<out Annotation>): Boolean
}