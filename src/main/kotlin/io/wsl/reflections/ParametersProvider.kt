package io.wsl.reflections

import java.lang.reflect.Method
import java.lang.reflect.Parameter

interface ParametersProvider {
    fun provide(method: Method): Array<Parameter>
}