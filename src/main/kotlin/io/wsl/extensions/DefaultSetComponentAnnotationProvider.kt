package io.wsl.extensions

import io.wsl.reflections.GenericAnnotationProvider
import org.springframework.stereotype.Component

@Component
class DefaultSetComponentAnnotationProvider(var annotationProvider: GenericAnnotationProvider) : SetComponentAnnotationProvider {
    override fun provide(clazz: Class<out Annotation>): SetComponent? {
        return annotationProvider.provide(clazz, SetComponent::class.java)
    }
}