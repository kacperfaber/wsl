package io.wsl.parameters

import java.lang.reflect.Parameter

interface ActionParameterProvider {
    fun provide(parameter: Parameter): ActionParameter
}