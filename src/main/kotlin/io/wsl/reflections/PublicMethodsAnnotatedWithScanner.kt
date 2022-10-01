package io.wsl.reflections

import java.lang.reflect.Method

interface PublicMethodsAnnotatedWithScanner {
    fun scan(clazz: Class<*>, annotationClass: Class<out Annotation>): List<Method>
}