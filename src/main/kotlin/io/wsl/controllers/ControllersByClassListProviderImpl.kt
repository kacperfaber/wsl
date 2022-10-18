package io.wsl.controllers

import io.wsl.SocketController
import io.wsl.classlist.ClassList
import io.wsl.classlist.ClassesAnnotatedWithProvider
import io.wsl.exceptions.CouldNotResolveAnnotation
import io.wsl.handlers.Handler
import io.wsl.listBuilder
import org.springframework.stereotype.Component

@Component
class ControllersByClassListProviderImpl(var controllerProvider: ControllerProvider, var controllerAnnotationProvider: SocketControllerAnnotationProvider, var classesAnnotatedWithProvider: ClassesAnnotatedWithProvider) : ControllersByClassListProvider {
    override fun provide(classList: ClassList, handlers: List<Handler>, defaultHandler: Handler?): List<Controller> = listBuilder {
        val classesAnnotated = classesAnnotatedWithProvider.provide(classList, SocketController::class.java)
        for (clazz in classesAnnotated) {
            val controllerAnnotation = controllerAnnotationProvider.provide(clazz) ?: throw CouldNotResolveAnnotation()
            val controller = controllerProvider.provide(clazz, controllerAnnotation, handlers, defaultHandler)
            yield(controller)
        }
    }
}