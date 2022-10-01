package io.wsl.reflections

import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class DefaultIsMethodAnnotatedWithChecker : IsMethodAnnotatedWithChecker {
    override fun check(method: Method, annotationClass: Class<out Annotation>): Boolean {
        return method.isAnnotationPresent(annotationClass)
    }
}