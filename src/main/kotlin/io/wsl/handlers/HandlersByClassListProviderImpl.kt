package io.wsl.handlers

import io.wsl.classlist.ClassList
import io.wsl.classlist.ClassesAnnotatedWithProvider
import io.wsl.exceptions.CouldNotResolveAnnotation
import io.wsl.listBuilder
import org.springframework.stereotype.Component

@Component
class HandlersByClassListProviderImpl(var handlerProvider: HandlerProvider, var handlerAnnotationProvider: HandlerAnnotationProvider, var classesAnnotatedWithProvider: ClassesAnnotatedWithProvider) : HandlersByClassListProvider {
    override fun provide(classList: ClassList): List<Handler> = listBuilder{
        val handlerClasses = classesAnnotatedWithProvider.provide(classList, io.wsl.Handler::class.java)
        for (handlerClass in handlerClasses) {
            val handlerAnnotation = handlerAnnotationProvider.provide(handlerClass) ?: throw CouldNotResolveAnnotation()
            val handler = handlerProvider.provide(handlerClass, handlerAnnotation)
            yield(handler)
        }
    }
}