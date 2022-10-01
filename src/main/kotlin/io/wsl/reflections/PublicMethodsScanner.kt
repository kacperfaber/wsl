package io.wsl.reflections

import java.lang.reflect.Method

interface PublicMethodsScanner {
    fun scan(clazz: Class<*>): List<Method>
}