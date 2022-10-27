package io.wsl.messages

import org.springframework.stereotype.Component

@Component
class DefaultMessageParser(var messageGenerator: MessageGenerator) : MessageParser {
    override fun parse(text: String): Message {
        try {
            val regex = Regex("(.+?) (.+?)")
            val match = regex.matchEntire(text)!!
            return messageGenerator.generate(match.groupValues[1], match.groupValues[2])
        }
        catch (e: Exception) {
            throw MessageParserException(text)
        }
    }
}