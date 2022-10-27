package io.wsl.executor

import io.wsl.actions.Action
import io.wsl.sessions.Session

interface SingleActionExecutor {
    fun execute(action: Action, session: Session, messageName: String, messageData: String): ExecutionResult
}