package io.wsl.action_call

import java.lang.reflect.Method

interface ActionCallProvider {
    fun provide(controllerInstance: Any, method: Method): ActionCall
}