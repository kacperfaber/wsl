package io.wsl.messages


interface MessageWriter {
    fun writeMessage(message: Message): String
}