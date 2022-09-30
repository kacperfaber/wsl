package io.wsl.controllers

import io.wsl.SocketController
import io.wsl.handlers.Handler

interface ControllerProvider {
    fun provide(clazz: Class<*>, socketController: SocketController, handlers: List<Handler>, defaultHandler: Handler?): Controller
}