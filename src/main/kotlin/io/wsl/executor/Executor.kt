package io.wsl.executor

import io.wsl.actions.Action
import org.springframework.web.socket.WebSocketSession

interface Executor {
    fun execute(actions: List<Action>, session: WebSocketSession, messageName: String, messageData: String)
}