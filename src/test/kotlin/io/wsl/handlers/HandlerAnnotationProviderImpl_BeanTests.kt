package io.wsl.handlers

import io.wsl.reflections.GenericAnnotationProvider
import io.wsl.tests.HandlerAnnotatedClass
import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotatedClass
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringTests
class HandlerAnnotationProviderImpl_BeanTests {
    @Autowired
    lateinit var provider: HandlerAnnotationProviderImpl

    @MockBean
    lateinit var annotationProvider: GenericAnnotationProvider

    fun handlerInstance(): io.wsl.Handler {
        return HandlerAnnotatedClass::class.java.getAnnotation(io.wsl.Handler::class.java)
    }

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide method does not throw`() {
        `when`(annotationProvider.provide(any(), any<Class<io.wsl.Handler>>())).thenReturn(null)
        assertDoesNotThrow { provider.provide(TestAnnotatedClass::class.java) }
    }

    @Test
    fun `provide returns null if genericAnnotationProvider returned null`() {
        `when`(annotationProvider.provide(any(), any<Class<io.wsl.Handler>>())).thenReturn(null)
        assertNull(provider.provide(TestAnnotatedClass::class.java))
    }
    
    @Test
    fun `provide returns instance equals to returned by genericAnnotationProvider`() {
        val handler = handlerInstance()
        `when`(annotationProvider.provide(any(), any<Class<io.wsl.Handler>>())).thenReturn(handler)
        assertEquals(handler, provider.provide(TestAnnotatedClass::class.java))
    }
}