package io.wsl.reflections

import io.wsl.tests.ParameterizedMethods
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class ParameterAnnotationsProvider_BeanTests {
    @Autowired
    lateinit var provider: ParameterAnnotationsProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw`() {
        val p1 = ParameterizedMethods.getSingleIntParamMethod().parameters[0]
        assertDoesNotThrow { provider.provide(p1) }
    }

    @Test
    fun `provide returns result equals to 'annotations' property`() {
        val p1 = ParameterizedMethods.getSingleIntParamMethod().parameters[0]
        assertEquals(p1.annotations, provider.provide(p1))
    }
}