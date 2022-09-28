package io.wsl.reflections

import io.wsl.tests.SecondAnnotation
import io.wsl.tests.TestAnnotation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultAnnotationClassComparer_compare_Tests {
    fun exec(c1: Class<out Annotation>, c2: Class<out Annotation>): Boolean {
        return DefaultAnnotationClassComparer().compare(c1, c2)
    }

    @Test
    fun `does not throw`() {
        assertDoesNotThrow { exec(TestAnnotation::class.java, TestAnnotation::class.java) }
    }

    @Test
    fun `returns true if expected`() {
        val res = exec(TestAnnotation::class.java, TestAnnotation::class.java)
        assertTrue { res }
    }

    @Test
    fun `returns false if expected`() {
        val res = exec(TestAnnotation::class.java, SecondAnnotation::class.java)
        assertFalse { res }
    }
}