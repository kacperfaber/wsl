package io.wsl.executor

import io.wsl.actions.Action
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

@Component
class DefaultExecutor(var singleActionExecutor: SingleActionExecutor) : Executor {
    override fun execute(actions: List<Action>, session: WebSocketSession, messageName: String, messageData: String) {
        for (action in actions) {
            val result = singleActionExecutor.execute(action, session, messageName, messageData)
            if (result == ExecutionResult.BREAK) break
        }
    }
}