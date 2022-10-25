package io.wsl.actions


interface ActionsByControllerProvider {
    fun provide(actions: List<Action>, controllerClass: Class<*>): List<Action>
}