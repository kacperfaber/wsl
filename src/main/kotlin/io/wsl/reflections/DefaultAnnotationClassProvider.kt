package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultAnnotationClassProvider : AnnotationClassProvider {
    override fun provide(annotation: Annotation): Class<out Annotation> = annotation.annotationClass.java
}