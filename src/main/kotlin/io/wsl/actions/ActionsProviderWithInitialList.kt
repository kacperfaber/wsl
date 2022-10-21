package io.wsl.actions

import io.wsl.controllers.Controller

interface ActionsProviderWithInitialList {
    fun provide(controller: Controller, initialList: MutableList<Action>): MutableList<Action>
}