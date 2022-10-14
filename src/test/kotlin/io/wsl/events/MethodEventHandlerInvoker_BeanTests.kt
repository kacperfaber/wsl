package io.wsl.events

import io.wsl.BeanOrNullProvider
import io.wsl.exceptions.EventHandlerInvokerException
import io.wsl.reflections.MethodInvoker
import io.wsl.tests.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.`when`
import org.mockito.internal.verification.Times
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.stream.Stream
import kotlin.test.assertNotNull

@SpringTests
class MethodEventHandlerInvoker_BeanTests {
    @Autowired
    lateinit var invoker: MethodEventHandlerInvoker

    @MockBean
    lateinit var beanProvider: BeanOrNullProvider

    @MockBean
    lateinit var methodInvoker: MethodInvoker

    @Test
    fun `not null`() {
        assertNotNull(invoker)
    }

    @Test
    fun `invoke does not throw when beanProvider returned instance`() {
        `when`(beanProvider.provide(any<Class<*>>())).thenReturn(Any())
        assertDoesNotThrow { invoker.invoke(5, TestComponent::class.java, WithMethodClass.annotatedMethod()) }
    }

    @Test
    fun `invoke throw EventHandlerInvokerException when beanProvider returns null`() {
        `when`(beanProvider.provide(any<Class<*>>())).thenReturn(null)
        assertThrows<EventHandlerInvokerException> {
            invoker.invoke(5, TestComponent::class.java, WithMethodClass.annotatedMethod())
        }
    }

    @Test
    fun `invoke calls beanProvider once`() {
        `when`(beanProvider.provide(any<Class<*>>())).thenReturn(null)
        try {invoker.invoke(5, TestComponent::class.java, WithMethodClass.annotatedMethod())}
        catch (e: Exception) {}
        verify(beanProvider, Times(1)).provide(any<Class<*>>())
    }

    @ParameterizedTest
    @MethodSource("classSource")
    fun `invoke calls beanProvider with componentClass value`(clazz: Class<*>) {
        val componentClass = TestComponent::class.java
        `when`(beanProvider.provide(any<Class<*>>())).thenReturn(null)
        try {invoker.invoke(5, componentClass, WithMethodClass.annotatedMethod())}
        catch(e: Exception) {}
        verify(beanProvider).provide(componentClass)
    }

    @Test
    fun `invoke calls methodInvoker zero times if beanProvider returned null`() {
        `when`(beanProvider.provide(any<Class<*>>())).thenReturn(null)
        try {
            invoker.invoke(5, TestComponent::class.java, WithMethodClass.annotatedMethod())
        }
        catch (e: Exception) {

        }
        verify(methodInvoker, Times(0)).invoke(any(), any(), any())
    }

    @Test
    fun `invoke calls methodInvoker once if beanProvider returned instance`() {
        `when`(beanProvider.provide(any<Class<*>>())).thenReturn(Any())
        invoker.invoke(5, TestComponent::class.java, WithMethodClass.annotatedMethod())
        verify(methodInvoker, Times(1)).invoke(any(), any(), any())
    }

    @Test
    fun `invoke calls methodInvoker with expected method`() {
        val method = WithMethodClass.annotatedMethod()
        `when`(beanProvider.provide(any<Class<*>>())).thenReturn(Any())
        invoker.invoke(5, TestComponent::class.java, WithMethodClass.annotatedMethod())
        verify(methodInvoker).invoke(eq(method), any(), any())
    }

    @ParameterizedTest
    @MethodSource("parameterInstanceSource")
    fun `invoke calls methodInvoker with component equals to returned by beanProvider`(component: Any) {
        `when`(beanProvider.provide(any<Class<*>>())).thenReturn(component)
        invoker.invoke(5, TestComponent::class.java, WithMethodClass.annotatedMethod())
        verify(methodInvoker).invoke(any(), eq(component), any())
    }

    @ParameterizedTest
    @MethodSource("parameterInstanceSource")
    fun `invoke calls methodInvoker with parameterInstance equals to given`(value: Any) {
        `when`(beanProvider.provide(any<Class<*>>())).thenReturn(5)
        invoker.invoke(value, TestComponent::class.java, WithMethodClass.annotatedMethod())
        verify(methodInvoker).invoke(any(), any(), eq(value))
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

        @JvmStatic
        fun parameterInstanceSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(TestComponent()),
                Arguments.of(50),
                Arguments.of("Hello World"),
                Arguments.of(TestError())
            )
        }
    }}