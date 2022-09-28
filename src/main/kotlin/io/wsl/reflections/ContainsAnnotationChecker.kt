package io.wsl.reflections

interface ContainsAnnotationChecker {
    fun contains(list: List<Annotation>, targetAnnotationClass: Class<out Annotation>): Boolean
}