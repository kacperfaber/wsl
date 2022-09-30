package io.wsl.reflections

import org.springframework.stereotype.Component

@Component
class DefaultClassAnnotationsProvider : ClassAnnotationsProvider {
    override fun provide(clazz: Class<*>): Array<Annotation> {
        return clazz.annotations
    }
}