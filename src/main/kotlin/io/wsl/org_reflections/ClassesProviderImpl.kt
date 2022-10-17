package io.wsl.org_reflections

import org.reflections.Reflections
import org.springframework.stereotype.Component

@Component
class ClassesProviderImpl : ClassesProvider {
    override fun provide(reflections: Reflections): Set<Class<*>> {
        return reflections.getSubTypesOf(Any::class.java)
    }
}