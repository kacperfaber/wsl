package io.wsl.extensions

import io.wsl.ArrayToListConverter
import io.wsl.reflections.ClassAnnotationsProvider
import io.wsl.tests.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.stream.Stream
import kotlin.reflect.full.createInstance
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class ExtensionsFromClassProvider_BeanTests {
    @Autowired
    lateinit var provider: ExtensionsFromClassProvider

    @MockBean
    lateinit var arrayConverter: ArrayToListConverter

    @MockBean
    lateinit var modelsProvider: ExtensionModelsProvider

    @MockBean
    lateinit var annotationsProvider: ClassAnnotationsProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw`() {
        `when`(arrayConverter.convert(any<Array<Annotation>>())).thenReturn(listOf())
        `when`(modelsProvider.provide(any())).thenReturn(listOf())
        `when`(annotationsProvider.provide(any())).thenReturn(arrayOf())

        assertDoesNotThrow { provider.provide(TestAnnotatedClass::class.java) }
    }

    @Test
    fun `provide returns what extensionModelsProvider returned`() {
        val expected = listOf<ExtensionModel>()

        `when`(arrayConverter.convert(any<Array<Annotation>>())).thenReturn(listOf())
        `when`(modelsProvider.provide(any())).thenReturn(expected)
        `when`(annotationsProvider.provide(any())).thenReturn(arrayOf())

        val res = provider.provide(TestAnnotatedClass::class.java)
        assertEquals(expected, res)
    }

    companion object {
        @JvmStatic
        fun classSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(TestAnnotatedClass::class.java),
                Arguments.of(HandlerAnnotatedClass::class.java),
                Arguments.of(TestComponent::class.java),
                Arguments.of(TestError::class.java)
            )
        }
    }

    @ParameterizedTest
    @MethodSource("classSource")
    fun `provide annotationsProvider called with given class`(cl: Class<*>) {
        `when`(arrayConverter.convert(any<Array<Annotation>>())).thenReturn(listOf())
        `when`(modelsProvider.provide(any())).thenReturn(listOf())
        `when`(annotationsProvider.provide(any())).thenReturn(arrayOf())

        provider.provide(cl)

        verify(annotationsProvider).provide(eq(cl))
    }

    @Test
    fun `provide arrayToListConverter called with what annotationsProvider returned`() {
        val annotation = TestAnnotation::class.createInstance()
        val expected = arrayOf<Annotation>(annotation)

        `when`(arrayConverter.convert(any<Array<Annotation>>())).thenReturn(listOf())
        `when`(modelsProvider.provide(any())).thenReturn(listOf())
        `when`(annotationsProvider.provide(any())).thenReturn(expected)

        provider.provide(TestAnnotatedClass::class.java)

        verify(arrayConverter).convert(eq(expected))
    }

    @Test
    fun `provide extensionModelsGenerator called with what arrayToListConverter returned`() {
        val annotation = TestAnnotation::class.createInstance()
        val expected = listOf<Annotation>(annotation)

        `when`(arrayConverter.convert(any<Array<Annotation>>())).thenReturn(expected)
        `when`(modelsProvider.provide(any())).thenReturn(listOf())
        `when`(annotationsProvider.provide(any())).thenReturn(arrayOf())

        provider.provide(TestAnnotatedClass::class.java)

        verify(modelsProvider).provide(eq(expected))
    }
}