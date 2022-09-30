package io.wsl.controllers

import io.wsl.SocketController
import io.wsl.reflections.GenericAnnotationProvider
import org.springframework.stereotype.Component

@Component
class DefaultSocketControllerAnnotationProvider(var genericAnnotationProvider: GenericAnnotationProvider) : SocketControllerAnnotationProvider {
    override fun provide(clazz: Class<*>): SocketController? {
        return genericAnnotationProvider.provide(clazz, SocketController::class.java)
    }
}