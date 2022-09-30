package io.wsl.handlers

import io.wsl.listBuilder
import io.wsl.reflections.ClassAnnotatedWithScanner
import org.springframework.stereotype.Component

@Component
class DefaultHandlersProvider(var classAnnotatedWithScanner: ClassAnnotatedWithScanner, var handlerProvider: HandlerProvider, var handlerAnnotationProvider: HandlerAnnotationProvider) : HandlersProvider {
    override fun provideAll(packagePrefix: String): List<Handler> = listBuilder{
        val classSet = classAnnotatedWithScanner.scan(packagePrefix, io.wsl.Handler::class.java)
        for (`class` in classSet) {
            val handlerAnnotation = handlerAnnotationProvider.provide(`class`) ?: continue
            val handler = handlerProvider.provide(`class`, handlerAnnotation)
            yield(handler)
        }
    }
}