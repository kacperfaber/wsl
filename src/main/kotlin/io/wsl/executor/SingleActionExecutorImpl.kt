package io.wsl.executor

import io.wsl.actions.Action
import io.wsl.sessions.Session
import org.springframework.stereotype.Component

@Component
class SingleActionExecutorImpl : SingleActionExecutor {
    override fun execute(action: Action, session: Session, messageName: String, messageData: String): ExecutionResult {
        TODO("Not yet implemented")
    }
}