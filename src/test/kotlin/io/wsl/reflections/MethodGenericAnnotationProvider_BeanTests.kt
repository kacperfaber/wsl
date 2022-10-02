package io.wsl.reflections

import io.wsl.tests.MethodAnnotation
import io.wsl.tests.SecondAnnotation
import io.wsl.tests.SpringTests
import io.wsl.tests.WithMethodClass
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringTests
class MethodGenericAnnotationProvider_BeanTests {
    @Autowired
    lateinit var provider: MethodGenericAnnotationProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw`() {
        assertDoesNotThrow { provider.provide(WithMethodClass.annotatedMethod(), SecondAnnotation::class.java) }
    }

    @Test
    fun `provide returns null if annotation not present`() {
        assertNull(provider.provide(WithMethodClass.notAnnotated(), MethodAnnotation::class.java))
    }

    @Test
    fun `provide returns not null if annotation is present`() {
        assertNotNull(provider.provide(WithMethodClass.annotatedMethod(), MethodAnnotation::class.java))
    }
}