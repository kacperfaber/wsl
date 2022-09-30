package io.wsl.handlers

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Repeat
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class HandlerGenerator_BeanTests {
    @Autowired
    lateinit var generator: HandlerGenerator

    @Test
    fun `not null`() {
        assertNotNull(generator)
    }

    @Test
    fun `generate method does not throw`() {
        assertDoesNotThrow { generator.generate("/", arrayOf("*"), false) }
    }
    
    @Test
    fun `generate returns not null`() {
        assertNotNull(generator.generate("", arrayOf(), false))
    }

    @ParameterizedTest
    @CsvSource("/chat", "/test", "/test/that", "/hello/world", "helloWorld", "helloWorld555", "^^^")
    fun `returns expected path as given`(path: String) {
        val res = generator.generate(path, arrayOf(), false)
        assertEquals(path, res.path)
    }

    @ParameterizedTest
    @CsvSource("helloWorld555", "^^^", "test5@#$%", "@$%^&&")
    fun `returns expected path as given when special characters were used`(path: String) {
        val res = generator.generate(path, arrayOf(), false)
        assertEquals(path, res.path)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `returns expected isDefault`(x: Boolean) {
        val res = generator.generate("", arrayOf(),x)
        assertEquals(x, res.isDefault)
    }
    
    @Test
    @Repeat(100)
    fun `returns allowedOrigins instance equals to given`() {
        val expected = arrayOf(UUID.randomUUID().toString())
        val res = generator.generate("", expected, false)
        assertEquals(expected, res.allowedOrigins)
    }
}