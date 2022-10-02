package io.wsl.reflections

import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class MethodGenericAnnotationProviderImpl : MethodGenericAnnotationProvider {
    override fun <T : Annotation> provide(method: Method, annotationClass: Class<T>): T? {
       return method.getAnnotation(annotationClass)
    }
}