package io.wsl.actions

import io.wsl.controllers.Controller

interface ActionsFromAllControllersProvider {
    fun provide(controllers: List<Controller>): List<Action>
}