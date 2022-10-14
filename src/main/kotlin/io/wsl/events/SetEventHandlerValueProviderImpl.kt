package io.wsl.events

import org.springframework.stereotype.Component

@Component
class SetEventHandlerValueProviderImpl(var setEventHandlerAnnotationProvider: SetEventHandlerAnnotationProvider) : SetEventHandlerValueProvider {
    override fun provide(annotations: Array<Annotation>): Class<*>? {
        val setEventHandler = setEventHandlerAnnotationProvider.provide(annotations)
        return setEventHandler?.clazz?.java
    }
}