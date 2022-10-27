package io.wsl.actions_picker

import io.wsl.actions.Action

interface ActionsPicker {
    fun pick(messageName: String, actions: List<Action>): List<Action>
}