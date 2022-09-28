package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultAnnotationIsAnnotatedChecker(var annotationClassProvider: AnnotationClassProvider, var annotationPresentChecker: IsAnnotationPresentChecker) : AnnotationIsAnnotatedChecker {
    override fun hasAnnotation(annotation: Annotation, targetAnnotationClass: Class<out Annotation>): Boolean {
        val `class` = annotationClassProvider.provide(annotation)
        return annotationPresentChecker.isAnnotationPresent(`class`, targetAnnotationClass)
    }
}