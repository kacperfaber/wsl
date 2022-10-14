package io.wsl.reflections

import java.lang.reflect.Method

interface MethodInvoker {
    fun invoke(method: Method, instance: Any, vararg params: Any?): Any?
}