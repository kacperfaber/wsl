package io.wsl.events

import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class DefaultMethodEventHandlerGenerator : MethodEventHandlerGenerator {
    override fun generate(key: Class<*>, componentClass: Class<*>, method: Method): MethodEventHandler {
        return MethodEventHandler(key, componentClass, method)
    }
}