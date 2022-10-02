package io.wsl.reflections

import java.lang.reflect.Method

interface MethodAnnotationsProvider {
    fun provide(method: Method): Array<Annotation>
}