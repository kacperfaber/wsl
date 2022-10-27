package io.wsl.actions_picker

import io.wsl.actions.Action
import org.springframework.stereotype.Component

@Component
class ActionsPickerImpl : ActionsPicker {
    override fun pick(messageName: String, actions: List<Action>): List<Action> {
        return actions.filter { it.pattern.matches(messageName) }
    }
}