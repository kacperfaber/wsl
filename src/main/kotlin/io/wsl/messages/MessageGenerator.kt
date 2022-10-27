package io.wsl.messages

interface MessageGenerator {
    fun generate(name: String, data: String): Message
}