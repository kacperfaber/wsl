package io.wsl.json

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

interface ObjectJsonSerializer {
    fun serialize(data: Any): String
}

@Component
class DefaultObjectJsonSerializer : ObjectJsonSerializer {
    private val objectMapper = ObjectMapper()

    override fun serialize(data: Any): String = objectMapper.writeValueAsString(data)
}