package io.wsl.events

import io.wsl.tests.EventHandlerAnnotation
import io.wsl.tests.SpringTests
import io.wsl.tests.WithMethodClass
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringTests
class EventHandlerValueProvider_BeanTests {
    @Autowired
    lateinit var provider: EventHandlerValueProvider

    @MockBean
    lateinit var annotationProvider: EventHandlerAnnotationProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw`() {
        `when`(annotationProvider.provide(any())).thenReturn(null)
        assertDoesNotThrow { provider.provide(WithMethodClass.annotatedMethod()) }
    }
    
    @Test
    fun `provide returns null if annotationProvider returned null`() {
        `when`(annotationProvider.provide(any())).thenReturn(null)
        assertNull(provider.provide(WithMethodClass.annotatedMethod()))
    }

    @Test
    fun `provide returns not null if annotationProvider returned instance`() {
        `when`(annotationProvider.provide(any())).thenReturn(EventHandlerAnnotation.getInstance1())
        assertNotNull(provider.provide(WithMethodClass.annotatedMethod()))
    }

    @Test
    fun `provide returns expected when annotationProvider returned instance EventHandlerAnnotation-getInstance1`() {
        val i1 = EventHandlerAnnotation.getInstance1()
        `when`(annotationProvider.provide(any())).thenReturn(i1)
        assertEquals(i1.clazz.java, provider.provide(WithMethodClass.annotatedMethod()))
    }

    @Test
    fun `provide returns expected when annotationProvider returned instance EventHandlerAnnotation-getInstance2`() {
        val i2 = EventHandlerAnnotation.getInstance2()
        `when`(annotationProvider.provide(any())).thenReturn(i2)
        assertEquals(i2.clazz.java, provider.provide(WithMethodClass.annotatedMethod()))
    }

    @Test
    fun `provide calls annotationProvider with method equals to given WithMethodClass-annotatedMethod`() {
        val expected = WithMethodClass.annotatedMethod()
        provider.provide(expected)
        verify(annotationProvider).provide(expected)
    }

    @Test
    fun `provide calls annotationProvider with method equals to given WithMethodClass-notAnnotated`() {
        val expected = WithMethodClass.notAnnotated()
        provider.provide(expected)
        verify(annotationProvider).provide(expected)
    }
}