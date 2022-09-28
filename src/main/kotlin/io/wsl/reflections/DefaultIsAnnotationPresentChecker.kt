package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultIsAnnotationPresentChecker : IsAnnotationPresentChecker {
    override fun isAnnotationPresent(clazz: Class<*>, annotationClass: Class<out Annotation>): Boolean {
        return clazz.isAnnotationPresent(annotationClass)
    }
}