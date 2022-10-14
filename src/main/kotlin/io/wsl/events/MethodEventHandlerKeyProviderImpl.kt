package io.wsl.events

import io.wsl.exceptions.InvalidEventHandlerMethodException
import io.wsl.reflections.MethodAnnotationsProvider
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class MethodEventHandlerKeyProviderImpl(var eventHandlerValueProvider: EventHandlerValueProvider, var annotationsProvider: MethodAnnotationsProvider, var setEventHandlerValueProvider: SetEventHandlerValueProvider) : MethodEventHandlerKeyProvider {
    override fun check(method: Method): Class<*>? {
        val annotations = annotationsProvider.provide(method)
        val setEventHandlerValue = setEventHandlerValueProvider.provide(annotations)
        val eventHandlerValue = eventHandlerValueProvider.provide(method)
        if (setEventHandlerValue != null && eventHandlerValue != null) {
            throw InvalidEventHandlerMethodException()
        }
        return setEventHandlerValue ?: eventHandlerValue
    }
}