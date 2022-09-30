package io.wsl.reflections

import org.reflections.Reflections
import org.springframework.stereotype.Component

@Component
class DefaultClassAnnotatedWithScanner : ClassAnnotatedWithScanner {
    override fun scan(packagePrefix: String, annotationClass: Class<out Annotation>): Set<Class<*>> {
        val reflections = Reflections(packagePrefix) // TODO: Use something else: Instance Provider? Bean? ...
        return reflections.getTypesAnnotatedWith(annotationClass)
    }
}