package io.wsl.messages

interface MessageParser {
    fun parse(text: String): Message
}