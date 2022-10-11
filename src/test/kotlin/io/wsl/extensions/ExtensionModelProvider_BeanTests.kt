package io.wsl.extensions

import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotation
import io.wsl.tests.TestComponent
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.mockito.internal.verification.Times
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.reflect.full.createInstance
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class ExtensionModelProvider_BeanTests {
    @Autowired
    lateinit var modelProvider: ExtensionModelProvider

    @MockBean
    lateinit var generator: ExtensionModelGenerator

    @Test
    fun `not null`() {
        assertNotNull(modelProvider)
    }

    @Test
    fun `provide does not throw`() {
        `when`(generator.generate(any(), any())).thenReturn(ExtensionModel(TestComponent::class.java, newTestAnnotation()))
        assertDoesNotThrow { modelProvider.provide(newTestAnnotation(), TestComponent::class.java) }
    }

    private fun newTestAnnotation(): Annotation {
        return TestAnnotation::class.createInstance()
    }

    @Test
    fun `provide returns result of ExtensionModelGenerator`() {
        val model = ExtensionModel(TestComponent::class.java, newTestAnnotation())
        `when`(generator.generate(any(), any())).thenReturn(model)
        assertEquals(model, modelProvider.provide(newTestAnnotation(), TestComponent::class.java))
    }

    @Test
    fun `provide calls ExtensionModelGenerator once`() {
        modelProvider.provide(newTestAnnotation(), TestComponent::class.java)
        verify(generator, Times(1)).generate(any(), any())
    }

    @Test
    fun `provide calls ExtensionModelGenerator with expected component class`() {
        val componentClass = TestComponent::class.java
        modelProvider.provide(newTestAnnotation(), componentClass)
        verify(generator).generate(eq(componentClass), any())
    }

    @Test
    fun `provide calls ExtensionModelGenerator with expected annotation`() {
        val annotation = newTestAnnotation()
        modelProvider.provide(annotation, TestComponent::class.java)
        verify(generator).generate(any(), eq(annotation))
    }
}