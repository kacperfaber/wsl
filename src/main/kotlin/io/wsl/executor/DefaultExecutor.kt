package io.wsl.executor

import io.wsl.actions.Action
import io.wsl.sessions.Session
import org.springframework.stereotype.Component

@Component
class DefaultExecutor(var singleActionExecutor: SingleActionExecutor) : Executor {
    override fun execute(actions: List<Action>, session: Session, messageName: String, messageData: String) {
        for (action in actions) {
            val result = singleActionExecutor.execute(action, session, messageName, messageData)
            if (result == ExecutionResult.BREAK) break
        }
    }
}