package io.wsl.reflections

import io.wsl.tests.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class ClassComparer_BeanTests {
    @Autowired
    lateinit var comparer: ClassComparer

    @Test
    fun `not null`() {
        assertNotNull(comparer)
    }

    @Test
    fun `compare does not throw when true if expected`() {
        assertDoesNotThrow {
            comparer.compare(TestAnnotatedClass::class.java, TestAnnotatedClass::class.java)
        }
    }

    @Test
    fun `compare does not throw when false if expected`() {
        assertDoesNotThrow { comparer.compare(TestComponent::class.java, SecondAnnotation::class.java) }
    }

    @Test
    fun `compare returns true if expected`() {
        assertTrue(comparer.compare(TestAnnotatedClass::class.java, TestAnnotatedClass::class.java))
        assertTrue(comparer.compare(HandlerAnnotatedClass::class.java, HandlerAnnotatedClass::class.java))
        assertTrue(comparer.compare(SecondAnnotation::class.java, SecondAnnotation::class.java))
        assertTrue(comparer.compare(TestComponent::class.java, TestComponent::class.java))
    }

    @Test
    fun `compare returns false if expected`() {
        assertFalse(comparer.compare(TestAnnotatedClass::class.java, HandlerAnnotatedClass::class.java))
        assertFalse(comparer.compare(SecondAnnotation::class.java, HandlerAnnotatedClass::class.java))
        assertFalse(comparer.compare(TestComponent::class.java, SecondAnnotation::class.java))
        assertFalse(comparer.compare(TestError::class.java, SecondAnnotation::class.java))
    }
}