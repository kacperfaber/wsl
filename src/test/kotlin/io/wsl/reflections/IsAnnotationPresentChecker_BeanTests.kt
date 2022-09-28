package io.wsl.reflections

import io.wsl.tests.SecondAnnotation
import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotatedClass
import io.wsl.tests.TestAnnotation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class IsAnnotationPresentChecker_BeanTests {
    @Autowired
    lateinit var checker: IsAnnotationPresentChecker

    @Test
    fun `not null`() {
        assertNotNull(checker)
    }

    @Test
    fun `isAnnotationPresent method does not throw`() {
        assertDoesNotThrow { checker.isAnnotationPresent(TestAnnotatedClass::class.java, TestAnnotation::class.java) }
    }

    @Test
    fun `isAnnotationPresent method returns true if expected`() {
        assertTrue { checker.isAnnotationPresent(TestAnnotatedClass::class.java, TestAnnotation::class.java) }
    }

    @Test
    fun `isAnnotationPresent method returns false if expected`() {
        assertFalse {checker.isAnnotationPresent(TestAnnotatedClass::class.java, SecondAnnotation::class.java) }
    }
}