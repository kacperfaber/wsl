package io.wsl.handlers

import io.wsl.extensions.ExtensionModel
import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotatedClass
import io.wsl.tests.TestComponent
import io.wsl.tests.TestError
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
        assertDoesNotThrow { generator.generate("/", arrayOf("*"), false, javaClass, mutableListOf()) }
    }

    @Test
    fun `generate returns not null`() {
        assertNotNull(generator.generate("", arrayOf(), false, javaClass, mutableListOf()))
    }

    @ParameterizedTest
    @CsvSource("/chat", "/test", "/test/that", "/hello/world", "helloWorld", "helloWorld555", "^^^")
    fun `returns expected path as given`(path: String) {
        val res = generator.generate(path, arrayOf(), false, javaClass, mutableListOf())
        assertEquals(path, res.path)
    }

    @ParameterizedTest
    @CsvSource("helloWorld555", "^^^", "test5@#$%", "@$%^&&")
    fun `returns expected path as given when special characters were used`(path: String) {
        val res = generator.generate(path, arrayOf(), false, javaClass, mutableListOf())
        assertEquals(path, res.path)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `returns expected isDefault`(x: Boolean) {
        val res = generator.generate("", arrayOf(), x, javaClass, mutableListOf())
        assertEquals(x, res.isDefault)
    }

    @Test
    @Repeat(100)
    fun `returns allowedOrigins instance equals to given`() {
        val expected = arrayOf(UUID.randomUUID().toString())
        val res = generator.generate("", expected, false, javaClass, mutableListOf())
        assertEquals(expected, res.allowedOrigins)
    }

    @ParameterizedTest
    @ValueSource(classes = [TestAnnotatedClass::class, TestComponent::class, TestError::class])
    fun `returns clazz equals to given`(expected: Class<*>) {
        val res = generator.generate("", arrayOf(), false, expected, mutableListOf())
        assertEquals(expected, res.clazz)
    }

    @Test
    fun `returns extensions list equals to given`() {
        val expected = mutableListOf<ExtensionModel>()
        val res = generator.generate("", arrayOf(), false, javaClass, expected)
        assertEquals(expected, res.extensions)
    }
}