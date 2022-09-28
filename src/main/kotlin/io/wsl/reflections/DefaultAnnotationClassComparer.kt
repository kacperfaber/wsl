package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultAnnotationClassComparer : AnnotationClassComparer {
    override fun compare(class1: Class<out Annotation>, class2: Class<out Annotation>): Boolean = class1 == class2
}