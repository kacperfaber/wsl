package io.wsl.messages

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class MessageGenerator_BeanTests {
    @Autowired
    lateinit var generator: MessageGenerator

    @Test
    fun `not null`() {
        assertNotNull(generator)
    }

    companion object {
        @JvmStatic
        fun source(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("Kacper", "Anetka"),
                Arguments.of("Hello", "World!"),
                Arguments.of("helloWorld", "{}"),
                Arguments.of("helloKacperek", "false"),
                Arguments.of("helloKacperek", "333")
            )
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `generate does not throw`(name: String, data: String) {
        assertDoesNotThrow { generator.generate(name, data) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `generate returns expected 'name'`(name: String, data: String) {
        assertEquals(name, generator.generate(name, data).name)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `generate returns expected 'data'`(name: String, data: String) {
        assertEquals(data, generator.generate(name, data).data)
    }
}