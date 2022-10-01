package io.wsl.reflections

import io.wsl.tests.MethodAnnotation
import io.wsl.tests.SpringTests
import io.wsl.tests.WithMethodClass
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class IsMethodAnnotatedWithChecker_BeanTests {
    @Autowired
    lateinit var checker: IsMethodAnnotatedWithChecker

    @Test
    fun `not null`() {
        assertNotNull(checker)
    }

    @Test
    fun `check method does not throw when true is expected`() {
        assertDoesNotThrow { checker.check(WithMethodClass.annotatedMethod(), MethodAnnotation::class.java) }
    }

    @Test
    fun `check method does not throw when false is expected`() {
        assertDoesNotThrow { checker.check(WithMethodClass.notAnnotated(), MethodAnnotation::class.java) }
    }

    @Test
    fun `check true if expected`() {
        assertTrue(checker.check(WithMethodClass.annotatedMethod(), MethodAnnotation::class.java))
    }

    @Test
    fun `check false if expected`() {
        assertFalse(checker.check(WithMethodClass.notAnnotated(), MethodAnnotation::class.java))
    }
}