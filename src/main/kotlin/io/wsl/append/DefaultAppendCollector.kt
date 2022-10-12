package io.wsl.append

import io.wsl.Append
import io.wsl.listBuilder
import io.wsl.reflections.SpecifiedAnnotationsByClassFromClassProvider
import org.springframework.stereotype.Component

@Component
class DefaultAppendCollector(var annotationsByClassFromClassProvider: SpecifiedAnnotationsByClassFromClassProvider) : AppendCollector {
    override fun collect(clazz: Class<*>): List<Class<*>> = listBuilder {
        for (append in annotationsByClassFromClassProvider.provide(clazz, Append::class.java)) {
            yield(append.clazz.java)
        }
    }
}