package io.wsl.controllers

import io.wsl.SocketController

interface SocketControllerAnnotationProvider {
    fun provide(clazz: Class<*>): SocketController?
}