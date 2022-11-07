package io.wsl.extensions

import io.wsl.reflections.GenericAnnotationByAnnotatedElementProvider
import org.springframework.stereotype.Component
import java.lang.reflect.AnnotatedElement

@Component
class SetExtensionKindAnnotationProviderImpl(val annotationFromAnnotatedElementProvider: GenericAnnotationByAnnotatedElementProvider) : SetExtensionKindAnnotationProvider {
    override fun provide(annotatedElement: AnnotatedElement): SetExtensionKind? {
        return annotationFromAnnotatedElementProvider.provide(annotatedElement, SetExtensionKind::class.java)
    }
}