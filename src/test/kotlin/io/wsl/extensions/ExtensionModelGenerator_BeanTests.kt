package io.wsl.extensions

import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotation
import io.wsl.tests.TestComponent
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import java.util.stream.Stream
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
        assertDoesNotThrow { extensionModelGenerator.generate(component, annotation, ExtensionKind.ModelValidator) }
    }

    @Test
    fun `generate returns not null`() {
        val annotation = TestAnnotation::class.createInstance()
        val component = TestComponent::class.java
        val res = extensionModelGenerator.generate(component, annotation, ExtensionKind.ModelValidator)
        assertNotNull(res)
    }

    @Test
    fun `generate returns annotation equals to given`() {
        val annotation = TestAnnotation::class.createInstance()
        val component = TestComponent::class.java
        val res = extensionModelGenerator.generate(component, annotation, ExtensionKind.ModelValidator)
        assertEquals(annotation, res.annotation)
    }

    @Test
    fun `generate returns componentClass equals to given`() {
        val annotation = TestAnnotation::class.createInstance()
        val component = TestComponent::class.java
        val res = extensionModelGenerator.generate(component, annotation, ExtensionKind.ModelValidator)
        assertEquals(component, res.componentClass)
    }

    companion object {
        @JvmStatic
        fun extensionKindSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(ExtensionKind.ModelValidator),
                Arguments.of(ExtensionKind.ParameterValidator),
                Arguments.of(ExtensionKind.ActionParameter),
                Arguments.of(ExtensionKind.PostAction),
                Arguments.of(ExtensionKind.PreAction)
            )
        }
    }

    @ParameterizedTest
    @MethodSource("extensionKindSource")
    fun `generate returns ExtensionKind equals to given`(extensionKind: ExtensionKind) {
        val annotation = TestAnnotation::class.createInstance()
        val component = TestComponent::class.java
        val res = extensionModelGenerator.generate(component, annotation, extensionKind)
        assertEquals(extensionKind, res.extensionKind)
    }
}