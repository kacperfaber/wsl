package io.wsl.reflections

import io.wsl.SocketAction
import io.wsl.actions.SocketActionAnnotationProvider
import io.wsl.tests.SampleSocketAnnotations
import io.wsl.tests.SecondAnnotation
import io.wsl.tests.SpringTests
import io.wsl.tests.WithMethodClass
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
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class SocketActionAnnotationProvider_BeanTests {
    @MockBean
    lateinit var methodProvider: MethodGenericAnnotationProvider

    @Autowired
    lateinit var provider: SocketActionAnnotationProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw`() {
        `when`(methodProvider.provide(WithMethodClass.annotatedMethod(), SecondAnnotation::class.java)).thenReturn(null)
        assertDoesNotThrow { provider.provide(WithMethodClass.annotatedMethod()) }
    }

    @MethodSource("sampleReturns")
    @ParameterizedTest
    fun `provide returns result of methodGenericAnnotationProvider`(annotation: SocketAction?) {
        `when`(methodProvider.provide(any(), any<Class<SocketAction>>()))
            .thenReturn(annotation)

        assertEquals(annotation, provider.provide(WithMethodClass.annotatedMethod()))
    }

    @Test
    fun `provide calls methodGenericAnnotationProvider with expected method`() {
        val method = WithMethodClass.annotatedMethod()
        provider.provide(method)
        verify(methodProvider).provide(eq(method), any<Class<SocketAction>>())
    }

    @Test
    fun `provide calls methodGenericAnnotationProvider with annotation class equals to SocketAction class`() {
        val method = WithMethodClass.annotatedMethod()
        provider.provide(method)
        verify(methodProvider).provide(any(), eq(SocketAction::class.java))
    }

    companion object {
        @JvmStatic
        fun sampleReturns(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(null),
                Arguments.of(SampleSocketAnnotations.pingMethod().getAnnotation(SocketAction::class.java))
            )
        }
    }
}