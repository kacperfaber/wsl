package io.wsl.actions

import io.wsl.controllers.Controller

interface ActionsProvider {
    fun provide(controller: Controller): List<Action>
}