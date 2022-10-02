package io.wsl.handlers

import io.wsl.exceptions.DefaultHandlerNotSet
import io.wsl.exceptions.HandlerNotResolved
import io.wsl.tests.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.stream.Stream
import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class HandlerForControllerProviderImpl_BeanTests {
    @Autowired
    lateinit var provider: HandlerForControllerProvider

    @MockBean
    lateinit var handlerProvider: HandlerByClassProvider

    fun newHandler(): Handler {
        return Handler("", arrayOf(), false, TestAnnotatedClass::class.java, mutableListOf())
    }

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw in good scenario`() {
        `when`(handlerProvider.provide(any(), anyOrNull()))
            .thenReturn(newHandler())

        assertDoesNotThrow { provider.provide(TestAnnotatedClass::class.java, TestAnnotatedClass::class.java, listOf(), null) }
    }

    @Test
    fun `provide throws HandlerNotResolved and setHandler is not null if handlerByClassProvider returned null`() {
        `when`(handlerProvider.provide(any(), any()))
            .thenReturn(null)

        assertThrows<HandlerNotResolved> {
            provider.provide(TestAnnotatedClass::class.java, TestAnnotatedClass::class.java, listOf(), null)
        }
    }

    @Test
    fun `provide throws DefaultHandlerNotSet when setHandler is null and defaultHandler is null`() {
        assertThrows<DefaultHandlerNotSet> {
            provider.provide(TestAnnotatedClass::class.java, null, listOf(), null)
        }
    }

    @Test
    fun `provide returns defaultHandler if setHandler is null`() {
        val handler = newHandler()
        val res = provider.provide(TestAnnotatedClass::class.java, null, listOf(), handler)
        assertEquals(handler, res)
    }

    @Test
    fun `provide returns value returned by handlerByClassProvider if setHandler is not null`() {
        val handler = newHandler()
        `when`(handlerProvider.provide(any(), any()))
            .thenReturn(handler)
        val res = provider.provide(TestAnnotatedClass::class.java, null, listOf(), handler)
        assertEquals(handler, res)
    }

    @ParameterizedTest
    @MethodSource("classSource")
    fun `provide calls handlerByClassProvider with class equals to setHandler given class`(setClass: Class<*>) {
        `when`(handlerProvider.provide(any(), any()))
            .thenReturn(newHandler())

        provider.provide(TestAnnotatedClass::class.java, setClass, listOf(), null)

        verify(handlerProvider).provide(any(), eq(setClass))
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