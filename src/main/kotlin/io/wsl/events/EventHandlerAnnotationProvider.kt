package io.wsl.events

import io.wsl.EventHandler
import java.lang.reflect.AnnotatedElement

interface EventHandlerAnnotationProvider {
    fun provide(annotatedElement: AnnotatedElement): EventHandler?
}