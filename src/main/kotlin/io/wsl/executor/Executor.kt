package io.wsl.executor

import io.wsl.actions.Action
import io.wsl.sessions.Session

interface Executor {
    fun execute(actions: List<Action>, session: Session, messageName: String, messageData: String)
}