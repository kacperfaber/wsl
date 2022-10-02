package io.wsl.parameters

import java.lang.reflect.Method

interface ParameterListProvider {
    fun provide(method: Method): ParameterList
}