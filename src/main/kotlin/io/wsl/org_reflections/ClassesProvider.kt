package io.wsl.org_reflections

import org.reflections.Reflections

interface ClassesProvider {
    fun provide(reflections: Reflections): Set<Class<*>>
}