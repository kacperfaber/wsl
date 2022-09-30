package io.wsl.controllers

import io.wsl.SocketController
import io.wsl.listBuilder
import io.wsl.reflections.ClassAnnotatedWithScanner
import org.springframework.stereotype.Component

@Component
class DefaultControllersProvider(var classAnnotatedWithScanner: ClassAnnotatedWithScanner, var controllerProvider: ControllerProvider, var socketControllerAnnotationProvider: SocketControllerAnnotationProvider) : ControllersProvider {
    override fun provideAll(packagePrefix: String): List<Controller> = listBuilder {
        val classList = classAnnotatedWithScanner.scan(packagePrefix, SocketController::class.java)
        for (`class` in classList) {
            val socketController = socketControllerAnnotationProvider.provide(`class`) ?: continue
            val controller = controllerProvider.provide(`class`, socketController)
            yield(controller)
        }
    }
}