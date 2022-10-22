package io.wsl.controllers

import io.wsl.handlers.Handler
import org.springframework.stereotype.Component

@Component
class ControllersByHandlerGrouperImpl : ControllersByHandlerGrouper {
    override fun map(controllers: List<Controller>): Map<Handler, List<Controller>> {
        return controllers.groupBy { it.handler }
    }
}