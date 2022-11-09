package io.wsl.executor

import io.wsl.actions.Action
import org.springframework.web.socket.WebSocketSession

interface SingleActionExecutor {
    fun execute(action: Action, session: WebSocketSession, messageName: String, messageData: String): ExecutionResult
}