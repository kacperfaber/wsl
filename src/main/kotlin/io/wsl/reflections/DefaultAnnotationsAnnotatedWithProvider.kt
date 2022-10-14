package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultAnnotationsAnnotatedWithProvider(var annotationIsAnnotatedChecker: AnnotationIsAnnotatedChecker) : AnnotationsAnnotatedWithProvider {
    override fun provide(annotations: Array<Annotation>, annotationClass: Class<out Annotation>): List<Annotation> {
        return annotations.filter {
            annotationIsAnnotatedChecker.hasAnnotation(it, annotationClass)
        }
    }
}