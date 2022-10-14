package io.wsl.events

import io.wsl.EventHandler
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class EventHandlerValueProviderImpl : EventHandlerValueProvider {
    override fun provide(method: Method): Class<*>? {
        return method.getAnnotation(EventHandler::class.java)?.clazz?.java
    }
}