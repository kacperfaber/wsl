package io.wsl.actions

import io.wsl.controllers.Controller
import io.wsl.listBuilder
import org.springframework.stereotype.Component

@Component
class DefaultActionsProvider(var socketActionAnnotationProvider: SocketActionAnnotationProvider, var actionProvider: ActionProvider, var actionMethodsProvider: SocketActionAnnotatedMethodsProvider) : ActionsProvider {
    override fun provide(controller: Controller): List<Action> = listBuilder {
        val methods = actionMethodsProvider.provide(controller.clazz)
        for (method in methods) {
            val socketAction = socketActionAnnotationProvider.provide(method) ?: continue
            val action = actionProvider.provide(method, socketAction, controller)
            yield(action)
        }
    }

}