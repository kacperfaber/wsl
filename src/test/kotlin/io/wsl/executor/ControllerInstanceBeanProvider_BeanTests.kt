package io.wsl.executor

import io.wsl.BeanOrNullProvider
import io.wsl.exceptions.ControllerNotRegistered
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class ControllerInstanceBeanProvider_BeanTests {
    @Autowired
    lateinit var provider: ControllerInstanceBeanProvider

    @MockBean
    lateinit var beanOrNullProvider: BeanOrNullProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw when beanOrNullProvider returned not null`() {
        `when`(beanOrNullProvider.provide(any<Class<*>>())).thenReturn(Any())
        assertDoesNotThrow { provider.provide(Any::class.java) }
    }

    @Test
    fun `provide throws ControllerNotRegistered when beanOrNullProvider returned null`() {
        `when`(beanOrNullProvider.provide(any<Class<*>>())).thenReturn(null)
        assertThrows<ControllerNotRegistered> { provider.provide(Any::class.java) }
    }

    @Test
    fun `provide throws ControllerNotRegistered with controllerClass equals to given when beanOrNullProvider returned null`() {
        `when`(beanOrNullProvider.provide(any<Class<*>>())).thenReturn(null)
        val controllerClass = Any::class.java
        try {
            provider.provide(controllerClass)
        }
        catch (e: ControllerNotRegistered) {
            assertEquals(controllerClass, e.controllerClass)
        }
    }

    @Test
    fun `provide returns instance returned by beanOrNullProvider`() {
        val nextInt = Random.nextInt()
        `when`(beanOrNullProvider.provide(any<Class<*>>())).thenReturn(nextInt)
        assertEquals(nextInt, provider.provide(Int::class.java))
    }
}