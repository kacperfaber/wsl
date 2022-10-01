package io.wsl.reflections

import java.lang.reflect.Method

interface IsMethodAnnotatedWithChecker {
    fun check(method: Method, annotationClass: Class<out Annotation>): Boolean
}