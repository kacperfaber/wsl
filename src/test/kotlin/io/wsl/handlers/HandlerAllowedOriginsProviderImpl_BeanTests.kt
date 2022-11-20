package io.wsl.handlers

import io.wsl.tests.HandlerAnnotatedClass
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class HandlerAllowedOriginsProviderImpl_BeanTests {
    @Autowired
    lateinit var allowedOriginsProvider: HandlerAllowedOriginsProvider

    val testHandler = HandlerAnnotatedClass.handlerInstance()

    @Test
    fun `not null`() {
        assertNotNull(allowedOriginsProvider)
    }

    @Test
    fun `provide returns result equals to path allowedOrigins`() {
        assertEquals(testHandler.allowedOrigins, allowedOriginsProvider.provide(testHandler))
    }
}