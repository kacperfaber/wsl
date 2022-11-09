package io.wsl.web_socket_handler

import io.wsl.actions.Action
import io.wsl.executor.Executor
import io.wsl.handlers.Handler
import io.wsl.messages.MessageParser
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler

@Component
class DefaultWsHandlerFactory(val executor: Executor, val messageParser: MessageParser, val eventPublisher: ApplicationEventPublisher) : WebSocketHandlerFactory {
    override fun createHandler(actions: List<Action>, handler: Handler): WebSocketHandler {
        return DefaultWsHandler(handler, actions, executor, messageParser, eventPublisher)
    }

}