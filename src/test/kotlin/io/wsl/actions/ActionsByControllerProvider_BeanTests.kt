package io.wsl.actions

import io.wsl.parameters.ParameterList
import io.wsl.reflections.ClassComparer
import io.wsl.tests.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

@SpringTests
class ActionsByControllerProvider_BeanTests {
    @Autowired
    lateinit var provider: ActionsByControllerProvider
    
    @MockBean
    lateinit var classComparer: ClassComparer
    
    @Test
    fun `not null`() {
        assertNotNull(provider)
    }
    
    @Test
    fun `provide does not throw`() {
        `when`(classComparer.compare(any(), any())).thenReturn(true)
        assertDoesNotThrow { provider.provide(listOf(), HandlerAnnotatedClass::class.java) }
    }
    
    private fun exampleAction(): Action {
        return Action(WithMethodClass.annotatedMethod(), HandlerAnnotatedClass::class.java, "", listOf(), ParameterList(), Regex(""))
    }

    @Test
    fun `provide asks ClassComparer for each element in given list`() {
        val list = listOf(exampleAction())
        provider.provide(list, HandlerAnnotatedClass::class.java)
        verify(classComparer, Times(list.count())).compare(any(), any())
    }
    
    @Test
    fun `provide returns list not instance equals to given list`() {
        val list = listOf(exampleAction())
        assertNotEquals(list, provider.provide(list, HandlerAnnotatedClass::class.java))
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
    fun `provide calls ClassComparer with given class as second parameter`(clazz: Class<*>) {
        val list = listOf(exampleAction())
        provider.provide(list, clazz)
        verify(classComparer).compare(any(), eq(clazz))
    }
}