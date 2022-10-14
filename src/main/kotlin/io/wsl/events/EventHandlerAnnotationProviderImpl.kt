package io.wsl.events

import io.wsl.EventHandler
import org.springframework.stereotype.Component
import java.lang.reflect.AnnotatedElement

@Component
class EventHandlerAnnotationProviderImpl : EventHandlerAnnotationProvider {
    override fun provide(annotatedElement: AnnotatedElement): EventHandler? {
        return annotatedElement.getAnnotation(EventHandler::class.java)
    }
}