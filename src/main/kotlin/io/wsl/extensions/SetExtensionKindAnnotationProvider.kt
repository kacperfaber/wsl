package io.wsl.extensions

import java.lang.reflect.AnnotatedElement

interface SetExtensionKindAnnotationProvider {
    fun provide(annotatedElement: AnnotatedElement): SetExtensionKind?
}