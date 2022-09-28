package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultContainsAnnotationChecker(var annotationClassComparer: AnnotationClassComparer) : ContainsAnnotationChecker {
    override fun contains(list: List<Annotation>, targetAnnotationClass: Class<out Annotation>): Boolean {
        return list.any { annotationClassComparer.compare(it.annotationClass.java, targetAnnotationClass) }
    }
}