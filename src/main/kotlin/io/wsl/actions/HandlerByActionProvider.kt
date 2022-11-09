package io.wsl.actions

import io.wsl.controllers.Controller
import io.wsl.handlers.Handler

interface HandlerByActionProvider {
    fun provide(action: Action, controllers: List<Controller>, handlers: List<Handler>): Handler
}