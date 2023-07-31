package io.wsl.json

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

interface ObjectJsonDeserializer {
    fun <T> deserialize(json: String, cl: Class<T>): T
}

@Component
class DefaultObjectJsonDeserializer : ObjectJsonDeserializer {
    private val objectMapper = ObjectMapper()

    override fun <T> deserialize(json: String, cl: Class<T>): T = objectMapper.readValue(json, cl)
}