package io.wsl.reflections

import java.lang.reflect.Parameter

interface ParameterAnnotationsProvider {
    fun provide(parameter: Parameter): Array<Annotation>
}