package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultGenericAnnotationProvider : GenericAnnotationProvider {
    override fun <T : Annotation> provide(clazz: Class<*>, annotationClass: Class<T>): T? {
        return clazz.getAnnotation(annotationClass)
    }
}