package io.wsl.actions

import io.wsl.SocketAction
import io.wsl.controllers.Controller
import java.lang.reflect.Method

interface ActionProvider {
    fun provide(method: Method, socketAction: SocketAction, controller: Controller): Action
}