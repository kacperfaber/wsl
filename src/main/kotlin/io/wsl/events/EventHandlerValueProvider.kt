package io.wsl.events

import java.lang.reflect.Method

interface EventHandlerValueProvider {
    fun provide(method: Method): Class<*>?
}