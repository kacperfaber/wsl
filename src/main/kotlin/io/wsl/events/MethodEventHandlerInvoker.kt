package io.wsl.events

import java.lang.reflect.Method

interface MethodEventHandlerInvoker {
    fun invoke(parameterInstance: Any, componentClass: Class<*>, method: Method)
}