package io.wsl.actions

interface ActionsByControllerGrouper {
    fun map(actions: List<Action>): Map<Class<*>, List<Action>>
}