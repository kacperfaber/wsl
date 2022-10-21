package io.wsl.handlers

import io.wsl.exceptions.MoreThanOneDefaultHandlerSet
import io.wsl.tests.HandlerAnnotatedClass
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringTests
class DefaultHandlerProviderOrNull_BeanTests {
    @Autowired
    lateinit var provider: DefaultHandlerProviderOrNull

    @MockBean
    lateinit var allDefaultHandlersProvider: AllDefaultHandlersProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    val exampleHandler = Handler(
        "", arrayOf(), false,
        HandlerAnnotatedClass::class.java,
        mutableListOf()
    )

    @Test
    fun `provide does not throw when allDefaultHandlersProvider returned not empty`() {
        `when`(allDefaultHandlersProvider.provide(any())).thenReturn(
            listOf(exampleHandler)
        )
        assertDoesNotThrow {
            provider.provide(listOf())
        }
    }

    @Test
    fun `provide returns null if allDefaultHandlersProvider returned empty list`() {
        `when`(allDefaultHandlersProvider.provide(any())).thenReturn(listOf())
        assertNull(provider.provide(listOf()))
    }

    @Test
    fun `provide throws MoreThanOneDefaultHandlerSet when allDefaultHandlersProvider returned more than 1 item`() {
        `when`(allDefaultHandlersProvider.provide(any())).thenReturn(
            listOf(exampleHandler, exampleHandler)
        )

        assertThrows<MoreThanOneDefaultHandlerSet> { provider.provide(listOf()) }
    }

    @Test
    fun `provide throws MoreThanOneDefaultHandlerSet when allDefaultHandlersProvider returned more than 1 item with list returned by allDefaultHandlersProvider`() {
        val expected = listOf(exampleHandler, exampleHandler)

        `when`(allDefaultHandlersProvider.provide(any())).thenReturn(
            expected
        )

        val exception = assertThrows<MoreThanOneDefaultHandlerSet> { provider.provide(listOf()) }
        assertEquals(expected, exception.foundDefaultHandlers)
    }

    @Test
    fun `provide returns list item when allDefaultHandlersProvider returned list with single element`() {
        `when`(allDefaultHandlersProvider.provide(any())).thenReturn(listOf(exampleHandler))
        assertEquals(exampleHandler, provider.provide(listOf()))
    }
}