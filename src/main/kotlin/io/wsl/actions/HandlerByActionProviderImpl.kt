package io.wsl.actions

import io.wsl.controllers.Controller
import io.wsl.handlers.Handler
import org.springframework.stereotype.Component

@Component
class HandlerByActionProviderImpl : HandlerByActionProvider {
    override fun provide(action: Action, controllers: List<Controller>, handlers: List<Handler>): Handler {
        val controller = controllers.first {it.clazz == action.controllerClass}
        return handlers.first {it.clazz == controller.handler.clazz}
    }
}