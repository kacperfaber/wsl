package io.wsl.reflections

import io.wsl.tests.SecondAnnotation
import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotatedClass
import io.wsl.tests.TestAnnotation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@SpringTests
class GenericAnnotationProvider_BeanTests {
    @Autowired
    lateinit var provider: GenericAnnotationProvider
    
    @Test
    fun `not null`() {
        assertNotNull(provider)
    }
    
    @Test
    fun `provide method does not throw`() {
        assertDoesNotThrow { provider.provide(TestAnnotatedClass::class.java, TestAnnotation::class.java) }
    }
    
    @Test
    fun `provide returns null if annotation missing`() {
        val res = provider.provide(TestAnnotatedClass::class.java, SecondAnnotation::class.java)
        assertNull(res)
    }
    
    @Test
    fun `provide returns not null if annotation present`() {
        val res = provider.provide(TestAnnotatedClass::class.java, TestAnnotation::class.java)
        assertNotNull(res)
    }
    
    @Test
    fun `provide returns instance of target annotation if annotation present`() {
        val res = provider.provide(TestAnnotatedClass::class.java, TestAnnotation::class.java)
        assertTrue { res is TestAnnotation }
    }
}