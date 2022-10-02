package io.wsl.actions

import io.wsl.extensions.ExtensionModel
import io.wsl.parameters.ParameterList
import io.wsl.tests.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class ActionGenerator_BeanTests {
    @Autowired
    lateinit var generator: ActionGenerator

    @Test
    fun `generate does not throw`() {
        assertDoesNotThrow { generator.generate("name", WithMethodClass.annotatedMethod(), WithMethodClass::class.java, ParameterList(), mutableListOf()) }
    }

    @Test
    fun `not null`() {
        assertNotNull(generator)
    }

    @ValueSource(strings = ["test1", "ping", "sayHello", "world"])
    @ParameterizedTest
    fun `generate returns name equals to given`(expected: String) {
        val res = generator.generate(expected, WithMethodClass.annotatedMethod(), WithMethodClass::class.java, ParameterList(), mutableListOf())
        assertEquals(expected, res.name)
    }

    @Test
    fun `generate returns method equals to given`() {
        val method = WithMethodClass.annotatedMethod()
        val res = generator.generate("hello", method, WithMethodClass::class.java, ParameterList(), mutableListOf())
        assertEquals(method, res.method)
    }

    @ParameterizedTest
    @MethodSource("classSource")
    fun `generate returns controllerClass equals to given`(clazz: Class<*>) {
        val res = generator.generate("hello", WithMethodClass.annotatedMethod(), clazz, ParameterList(), mutableListOf())
        assertEquals(clazz, res.controllerClass)
    }

    companion object {
        @JvmStatic
        fun classSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(TestAnnotatedClass::class.java),
                Arguments.of(HandlerAnnotatedClass::class.java),
                Arguments.of(TestComponent::class.java),
                Arguments.of(TestError::class.java)
            )
        }
    }

    @Test
    fun `generate returns parameterList equals to given`() {
        val expected = ParameterList()
        val res = generator.generate("hello", WithMethodClass.annotatedMethod(), WithMethodClass::class.java,
            expected, mutableListOf())
        assertEquals(expected, res.parameterList)
    }

    @Test
    fun `generate returns extensions equals to given`() {
        val expected = mutableListOf<ExtensionModel>()
        val res = generator.generate("hello", WithMethodClass.annotatedMethod(), WithMethodClass::class.java,
            ParameterList(), expected)
        assertEquals(expected, res.extensions)
    }
}