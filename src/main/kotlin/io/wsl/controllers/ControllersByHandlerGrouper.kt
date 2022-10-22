package io.wsl.controllers

import io.wsl.handlers.Handler

interface ControllersByHandlerGrouper {
    fun map(controllers: List<Controller>): Map<Handler, List<Controller>>
}