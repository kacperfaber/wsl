package io.wsl.web_socket_handler

import io.wsl.actions.Action
import io.wsl.executor.Executor
import io.wsl.handlers.Handler
import io.wsl.messages.MessageParser
import io.wsl.spring_events.ConnectionClosed
import io.wsl.spring_events.ConnectionEstablished
import io.wsl.spring_events.MessageReceived
import io.wsl.spring_events.TransportError
import org.springframework.context.ApplicationEventPublisher
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession

class DefaultWsHandler(
    val handler: Handler,
    val actions: List<Action>,
    val executor: Executor,
    val messageProvider: MessageParser,
    val eventPublisher: ApplicationEventPublisher
) : WebSocketHandler {
    override fun afterConnectionEstablished(session: WebSocketSession) {
        eventPublisher.publishEvent(ConnectionEstablished(session))
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        val text = message.payload.toString()
        val msg = messageProvider.parse(text)
        executor.execute(actions, session, msg.name, msg.data)
        eventPublisher.publishEvent(MessageReceived(text, session))
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        eventPublisher.publishEvent(TransportError(session, exception))
    }

    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {
        eventPublisher.publishEvent(ConnectionClosed(session, closeStatus))
    }

    override fun supportsPartialMessages(): Boolean {
        return false
    }
}