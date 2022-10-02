package io.wsl.actions

import java.lang.reflect.Method

interface SocketActionAnnotatedMethodsProvider {
    fun provide(clazz: Class<*>): List<Method>
}