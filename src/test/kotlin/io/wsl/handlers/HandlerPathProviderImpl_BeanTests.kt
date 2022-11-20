package io.wsl.handlers

import io.wsl.Handler
import io.wsl.tests.HandlerAnnotatedClass
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class HandlerPathProviderImpl_BeanTests {
    @Autowired
    lateinit var pathProvider: HandlerPathProviderImpl

    val testHandler = HandlerAnnotatedClass.handlerInstance()

    @Test
    fun `not null`() {
        assertNotNull(pathProvider)
    }

    @Test
    fun `provide returns result equals to path value`() {
        assertEquals(testHandler.path, pathProvider.provide(testHandler))
    }
}