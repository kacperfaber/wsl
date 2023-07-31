package io.wsl.messages.messaging

import io.wsl.messages.DataWriter
import io.wsl.messages.MessageGenerator
import io.wsl.messages.MessageWriter
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Component
class MessagingService(private val messageGenerator: MessageGenerator, private val messageWriter: MessageWriter, private val dataWriter: DataWriter) {
    fun <T> send(session: WebSocketSession, name: String, data: T) {
        val message = messageGenerator.generate(name, dataWriter.writeData(data))
        val messageStr = messageWriter.writeMessage(message)
        val messageToSend = TextMessage(messageStr)
        session.sendMessage(messageToSend)
    }
}