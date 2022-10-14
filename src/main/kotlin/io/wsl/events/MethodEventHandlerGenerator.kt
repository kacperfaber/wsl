package io.wsl.events

import java.lang.reflect.Method

interface MethodEventHandlerGenerator {
    fun generate(key: Class<*>, componentClass: Class<*>, method: Method): MethodEventHandler
}