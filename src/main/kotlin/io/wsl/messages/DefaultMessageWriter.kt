package io.wsl.messages

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class DefaultMessageWriter : MessageWriter {
    private val objectMapper = ObjectMapper()

    override fun writeMessage(message: Message): String {
        val dataJson = objectMapper.writeValueAsString(message.data)
        return "${message.name} $dataJson"
    }
}