package io.wsl.controllers

import io.wsl.SocketController
import io.wsl.reflections.GenericAnnotationProvider
import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotatedClass
import io.wsl.tests.TestComponent
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.reflect.full.createInstance
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringTests
class SocketControllerAnnotationProvider_BeanTests {
    @Autowired
    lateinit var provider: SocketControllerAnnotationProvider
    
    @MockBean
    lateinit var annotationProvider: GenericAnnotationProvider

    fun newInstance() = SocketController::class.createInstance()
    
    @Test
    fun `not null`() {
        assertNotNull(provider)
    }
    
    @Test
    fun `provide method does not throw`() {
        `when`(annotationProvider.provide(any(), any<Class<SocketController>>())).thenReturn(null)
        assertDoesNotThrow { provider.provide(TestAnnotatedClass::class.java) }
    }

    @Test
    fun `provide returns null if genericAnnotationProvider returned null`() {
        `when`(annotationProvider.provide(any(), any<Class<SocketController>>())).thenReturn(null)
        assertNull(provider.provide(TestAnnotatedClass::class.java))
    }

    @Test
    fun `provide returns instance returned by genericAnnotationProvider`() {
        val instance = newInstance()
        `when`(annotationProvider.provide(any(), any<Class<SocketController>>())).thenReturn(instance)
        assertEquals(instance, provider.provide(TestAnnotatedClass::class.java))
    }

    @Test
    fun `provide calls genericAnnotationProvider with class set to SocketHandler`() {
        `when`(annotationProvider.provide(any(), any<Class<SocketController>>())).thenReturn(null)
        provider.provide(TestAnnotatedClass::class.java)
        verify(annotationProvider).provide(any(), eq(SocketController::class.java))
    }

    @Test
    fun `provide calls genericAnnotationProvider with given class`() {
        val clazz = TestAnnotatedClass::class.java
        `when`(annotationProvider.provide(any(), any<Class<SocketController>>())).thenReturn(null)
        provider.provide(clazz)
        verify(annotationProvider).provide(eq(clazz), any<Class<SocketController>>())
    }
}