package io.wsl.events

import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class EventHandlerValueProviderImpl(var annotationProvider: EventHandlerAnnotationProvider) : EventHandlerValueProvider {
    override fun provide(method: Method): Class<*>? {
        val annotation = annotationProvider.provide(method)
        return annotation?.clazz?.java
    }
}