package io.wsl.extensions

import org.springframework.stereotype.Component
import java.lang.reflect.AnnotatedElement

@Component
class ExtensionKindValueProviderImpl(var setExtensionKindAnnotationProvider: SetExtensionKindAnnotationProvider) : ExtensionKindValueProvider {
    override fun provide(annotatedElement: AnnotatedElement): ExtensionKind? {
        return setExtensionKindAnnotationProvider.provide(annotatedElement)?.extensionKind
    }
}