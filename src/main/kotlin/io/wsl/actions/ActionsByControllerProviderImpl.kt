package io.wsl.actions

import io.wsl.reflections.ClassComparer
import org.springframework.stereotype.Component

@Component
class ActionsByControllerProviderImpl(var classComparer: ClassComparer) : ActionsByControllerProvider {
    override fun provide(actions: List<Action>, controllerClass: Class<*>): List<Action> {
        return actions.filter { classComparer.compare(it.controllerClass, controllerClass) }
    }
}