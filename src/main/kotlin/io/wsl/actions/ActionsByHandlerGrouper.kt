package io.wsl.actions

import io.wsl.controllers.Controller
import io.wsl.handlers.Handler

interface ActionsByHandlerGrouper {
    fun group(actions: List<Action>, controllers: List<Controller>, handlers: List<Handler>): Map<Handler, List<Action>>
}