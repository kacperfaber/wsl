package io.wsl.messages

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

interface DataWriter {
    fun <T> writeData(data: T): String
}

@Component
class DefaultDataWriter() : DataWriter {
    override fun <T> writeData(data: T): String {
        return ObjectMapper().writeValueAsString(data)
    }
}