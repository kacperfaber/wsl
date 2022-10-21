package io.wsl.actions

import io.wsl.controllers.Controller
import org.springframework.stereotype.Component

@Component
class ActionsFromAllControllersProviderImpl(var actionsProviderWithInitialList: ActionsProviderWithInitialList) : ActionsFromAllControllersProvider {
    override fun provide(controllers: List<Controller>): List<Action> {
        val list = mutableListOf<Action>()
        for (controller in controllers) {
            actionsProviderWithInitialList.provide(controller, list)
        }
        return list
    }

}