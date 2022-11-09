package io.wsl.actions

import io.wsl.controllers.Controller
import io.wsl.handlers.Handler
import org.springframework.stereotype.Component

@Component
class ActionsByHandlerGrouperImpl(val handlerProvider: HandlerByActionProvider) : ActionsByHandlerGrouper {
    override fun group(
        actions: List<Action>,
        controllers: List<Controller>,
        handlers: List<Handler>
    ): Map<Handler, List<Action>> {

        return actions.groupBy { handlerProvider.provide(it, controllers, handlers) }

    }
}