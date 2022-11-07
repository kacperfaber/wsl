package io.wsl.extensions

import java.lang.reflect.AnnotatedElement

interface ExtensionKindValueProvider {
    fun provide(annotatedElement: AnnotatedElement): ExtensionKind?
}