package io.wsl.action_call

import io.wsl.tests.ParameterizedMethods
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class ActionCallProvider_BeanTests {
    @Autowired
    lateinit var provider: ActionCallProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw`() {
        val method = ParameterizedMethods.getSingleIntParamMethod()
        val instance = Any()
        assertDoesNotThrow { provider.provide(instance, method, "name", "{}") }
    }

    @Test
    fun `provide returns 'method' equals to given`() {
        val method = ParameterizedMethods.getSingleIntParamMethod()
        val instance = Any()
        assertEquals(method, provider.provide(instance, method, "name", "{}").method)
    }

    @Test
    fun `provide returns 'controllerInstance' equals to given`() {
        val method = ParameterizedMethods.getSingleIntParamMethod()
        val instance = Any()
        assertEquals(instance, provider.provide(instance, method, "name", "{}").controllerInstance)
    }

    @Test
    fun `provide returns empty 'parameters'`() {
        val method = ParameterizedMethods.getSingleIntParamMethod()
        val instance = Any()
        assertTrue(provider.provide(instance, method, "name", "{}").parameters.isEmpty())
    }

    @Test
    fun `provide returns 'messageName' equals to given`() {
        val expected = "exp_name_123"
        val method = ParameterizedMethods.getSingleIntParamMethod()
        val instance = Any()
        assertEquals(expected, provider.provide(instance, method, expected, "{}").messageName)
    }

    @Test
    fun `provide returns 'messageData' equals to given`() {
        val expected = "exp_data_123"
        val method = ParameterizedMethods.getSingleIntParamMethod()
        val instance = Any()
        assertEquals(expected, provider.provide(instance, method, "name", expected).messageData)
    }
}