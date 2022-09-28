package io.wsl.extensions

import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotation
import io.wsl.tests.TestComponent
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.reflect.full.createInstance
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class ExtensionModelGenerator_BeanTests {
    @Autowired
    lateinit var extensionModelGenerator: ExtensionModelGenerator

    @Test
    fun `not null`() {
        assertNotNull(extensionModelGenerator)
    }

    @Test
    fun `generate method not throws`() {
        val annotation = TestAnnotation::class.createInstance()
        val component = TestComponent::class.java
        assertDoesNotThrow { extensionModelGenerator.generate(component, annotation) }
    }

    @Test
    fun `generate returns not null`() {
        val annotation = TestAnnotation::class.createInstance()
        val component = TestComponent::class.java
        val res = extensionModelGenerator.generate(component, annotation)
        assertNotNull(res)
    }

    @Test
    fun `generate returns annotation equals to given`() {
        val annotation = TestAnnotation::class.createInstance()
        val component = TestComponent::class.java
        val res = extensionModelGenerator.generate(component, annotation)
        assertEquals(annotation, res.annotation)
    }

    @Test
    fun `generate returns componentClass equals to given`() {
        val annotation = TestAnnotation::class.createInstance()
        val component = TestComponent::class.java
        val res = extensionModelGenerator.generate(component, annotation)
        assertEquals(component, res.componentClass)
    }
}