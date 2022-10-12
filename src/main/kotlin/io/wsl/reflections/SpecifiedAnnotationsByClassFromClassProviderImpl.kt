package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class SpecifiedAnnotationsByClassFromClassProviderImpl(var annotationClassComparer: AnnotationClassComparer) : SpecifiedAnnotationsByClassFromClassProvider {
    @Suppress("UNCHECKED_CAST")
    override fun <T> provide(clazz: Class<*>, annotationClass: Class<T>): List<T> where T : Annotation {
        // TODO: Make this better.
        return clazz.annotations
            .filter { annotationClassComparer.compare(it.annotationClass.java, annotationClass) }
            .map { it as T }
    }
}