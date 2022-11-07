package io.wsl.reflections

import org.springframework.stereotype.Component
import java.lang.reflect.AnnotatedElement

@Component
class GenericAnnotationByAnnotatedElementProviderImpl : GenericAnnotationByAnnotatedElementProvider {
    override fun <T : Annotation> provide(annotatedElement: AnnotatedElement, annotationClass: Class<out T>): T? {
        return annotatedElement.getAnnotation(annotationClass)
    }
}