package io.wsl.web_socket_handler

import io.wsl.actions.Action
import io.wsl.handlers.Handler
import org.springframework.web.socket.WebSocketHandler

interface WebSocketHandlerFactory {
    fun createHandler(actions: List<Action>, handler: Handler): WebSocketHandler
}