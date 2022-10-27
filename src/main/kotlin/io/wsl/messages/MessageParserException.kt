package io.wsl.messages

class MessageParserException(messageText: String) : Exception("'$messageText' could not be parsed.")