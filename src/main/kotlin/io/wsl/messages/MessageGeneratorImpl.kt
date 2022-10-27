package io.wsl.messages

import org.springframework.stereotype.Component

@Component
class MessageGeneratorImpl : MessageGenerator {
    override fun generate(name: String, data: String): Message {
        return Message(name, data)
    }
}