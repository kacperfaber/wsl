package io.wsl.events

import io.wsl.tests.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.reflect.Method
import java.util.stream.Stream
import kotlin.test.assertEquals

class DefaultMethodEventHandlerGenerator_generate_Tests {
    private fun exec(key: Class<*>, componentClass: Class<*>, method: Method): MethodEventHandler {
        return DefaultMethodEventHandlerGenerator().generate(key, componentClass, method)
    }

    @Test
    fun `does not throw`() {
        assertDoesNotThrow { exec(TestComponent::class.java, TestComponent::class.java, WithMethodClass.annotatedMethod()) }
    }

    @ParameterizedTest
    @MethodSource("classSource")
    fun `expected key`(key: Class<*>) {
        assertEquals(key, exec(key, TestComponent::class.java, WithMethodClass.annotatedMethod()).key)
    }

    @ParameterizedTest
    @MethodSource("classSource")
    fun `expected componentClass`(componentClass: Class<*>) {
        assertEquals(
            componentClass,
            exec(TestComponent::class.java, componentClass, WithMethodClass.annotatedMethod()).componentClass
        )
    }

    @Test
    fun `expected method`() {
        val annotatedMethod = WithMethodClass.annotatedMethod()
        val method = WithMethodClass.notAnnotated()
        assertEquals(method, exec(TestComponent::class.java, TestComponent::class.java, method).method)
        assertEquals(annotatedMethod, exec(TestComponent::class.java, TestComponent::class.java, annotatedMethod).method)
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
}