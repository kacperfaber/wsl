package io.wsl.include

import io.wsl.Include
import io.wsl.listBuilder
import io.wsl.reflections.SpecifiedAnnotationsByClassFromClassProvider
import org.springframework.stereotype.Component

@Component
class DefaultIncludeCollector(var annotationsByClassFromClassProvider: SpecifiedAnnotationsByClassFromClassProvider) : IncludeCollector {
    override fun collect(clazz: Class<*>): List<Class<*>> = listBuilder {
        for (append in annotationsByClassFromClassProvider.provide(clazz, Include::class.java)) {
            yield(append.clazz.java)
        }
    }
}