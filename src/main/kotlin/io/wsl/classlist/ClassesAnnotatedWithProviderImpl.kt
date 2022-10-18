package io.wsl.classlist

import io.wsl.reflections.IsAnnotationPresentChecker
import org.springframework.stereotype.Component

@Component
class ClassesAnnotatedWithProviderImpl(var annotationPresentChecker: IsAnnotationPresentChecker) : ClassesAnnotatedWithProvider {
    override fun provide(classList: ClassList, annotationClass: Class<out Annotation>): List<Class<*>> {
        return classList.filter { annotationPresentChecker.isAnnotationPresent(it, annotationClass) }
    }
}