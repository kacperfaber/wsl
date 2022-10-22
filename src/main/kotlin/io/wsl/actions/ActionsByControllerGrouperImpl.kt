package io.wsl.actions

import org.springframework.stereotype.Component

@Component
class ActionsByControllerGrouperImpl : ActionsByControllerGrouper {
    override fun map(actions: List<Action>): Map<Class<*>, List<Action>> {
        return actions.groupBy { it.controllerClass }
    }
}