package io.wsl.events

import java.lang.reflect.Method

interface MethodEventHandlerKeyProvider {
    fun check(method: Method): Class<*>?
}