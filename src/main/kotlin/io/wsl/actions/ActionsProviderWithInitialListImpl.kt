package io.wsl.actions

import io.wsl.controllers.Controller
import io.wsl.listBuilderFrom
import org.springframework.stereotype.Component

@Component
class ActionsProviderWithInitialListImpl(var socketActionAnnotationProvider: SocketActionAnnotationProvider, var actionProvider: ActionProvider, var actionMethodsProvider: SocketActionAnnotatedMethodsProvider) : ActionsProviderWithInitialList {
    override fun provide(controller: Controller, initialList: MutableList<Action>): MutableList<Action> = listBuilderFrom(initialList) {
        val methods = actionMethodsProvider.provide(controller.clazz)
        for (method in methods) {
            val socketAction = socketActionAnnotationProvider.provide(method) ?: continue
            val action = actionProvider.provide(method, socketAction, controller)
            yield(action)
        }
    }
}