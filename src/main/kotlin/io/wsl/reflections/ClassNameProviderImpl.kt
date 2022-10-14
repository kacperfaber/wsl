package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class ClassNameProviderImpl : ClassNameProvider {
    override fun provide(clazz: Class<*>): String {
        return clazz.name
    }
}