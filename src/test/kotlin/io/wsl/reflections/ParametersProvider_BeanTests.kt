package io.wsl.reflections

import io.wsl.tests.ParameterizedMethods
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class ParametersProvider_BeanTests {
    @Autowired
    lateinit var provider: ParametersProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw`() {
        assertDoesNotThrow { provider.provide(ParameterizedMethods.getSingleIntParamMethod()) }
    }

    @Test
    fun `expected item count`() {
        assertTrue(provider.provide(ParameterizedMethods.getSingleIntParamMethod()).count() == 1)
    }

    @Test
    fun `expected parameter type`() {
        assertEquals(Int::class.java, provider.provide(ParameterizedMethods.getSingleIntParamMethod())[0].type)
    }
}