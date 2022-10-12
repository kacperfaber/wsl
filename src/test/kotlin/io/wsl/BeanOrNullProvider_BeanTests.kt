package io.wsl

import io.wsl.tests.SpringTests
import io.wsl.tests.TestComponent
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringTests
class BeanOrNullProvider_BeanTests {
    @Autowired
    lateinit var orNullProvider: BeanOrNullProvider

    @MockBean
    lateinit var beanProvider: BeanProvider

    @Test
    fun `not null`() {
        assertNotNull(orNullProvider)
    }

    @Test
    fun `provide does not throw`() {
        `when`(beanProvider.provide(any<Class<TestComponent>>())).thenThrow(NoSuchBeanDefinitionException::class.java)
        assertDoesNotThrow { orNullProvider.provide(TestComponent::class.java) }
    }

    @Test
    fun `provide returns null if beanProvider threw`() {
        `when`(beanProvider.provide(any<Class<TestComponent>>())).thenThrow(NoSuchBeanDefinitionException::class.java)
        assertNull(orNullProvider.provide(TestComponent::class.java))
    }
}