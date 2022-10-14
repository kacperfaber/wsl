package io.wsl.events

import java.lang.reflect.Method

interface MethodEventHandlerGenerator {
    fun generate(componentClass: Class<*>, method: Method): MethodEventHandler
}