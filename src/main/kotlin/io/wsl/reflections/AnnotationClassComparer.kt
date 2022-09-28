package io.wsl.reflections

interface AnnotationClassComparer {
    fun compare(class1: Class<out Annotation>, class2: Class<out Annotation>): Boolean
}