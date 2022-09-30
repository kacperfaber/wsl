package io.wsl.handlers

import io.wsl.reflections.IsAnnotationPresentChecker
import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotatedClass
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class DefaultHandlerClassIsDefaultHandlerChecker_BeanTests {
    @Autowired
    lateinit var checker: DefaultHandlerClassIsDefaultHandlerChecker

    @MockBean
    lateinit var presentChecker: IsAnnotationPresentChecker

    @Test
    fun `not null`() {
        assertNotNull(checker)
    }

    @Test
    fun `check method does not throw`() {
        `when`(presentChecker.isAnnotationPresent(any(), any())).thenReturn(true)
        assertDoesNotThrow { checker.check(TestAnnotatedClass::class.java) }
    }

    @Test
    fun `check method returns true if annotation present checker returned true`() {
        `when`(presentChecker.isAnnotationPresent(any(), any())).thenReturn(true)
        assertTrue { checker.check(TestAnnotatedClass::class.java) }
    }

    @Test
    fun `check method returns false if annotation present checker returned false`() {
        `when`(presentChecker.isAnnotationPresent(any(), any())).thenReturn(false)
        assertFalse { checker.check(TestAnnotatedClass::class.java) }
    }
}