package io.wsl.handlers

import io.wsl.tests.SpringTests
import io.wsl.tests.TestAnnotatedClass
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class HandlerInstanceComparer_BeanTests {
    @Autowired
    lateinit var comparer: HandlerInstanceComparer

    @Test
    fun `not null`() {
        assertNotNull(comparer)
    }

    @Test
    fun `compare does not throw`() {
        val handler = Handler("", arrayOf(), false, TestAnnotatedClass::class.java, listOf())
        assertDoesNotThrow { comparer.compare(handler, handler) }
    }

    @Test
    fun `compare returns true if instance are the same`() {
        val handler = Handler("", arrayOf(), false, TestAnnotatedClass::class.java, listOf())
        assertTrue { comparer.compare(handler, handler) }
    }

    @Test
    fun `compare returns false if has other references`() {
        val handler = Handler("", arrayOf(), false, TestAnnotatedClass::class.java, listOf())
        val anotherHandler = Handler("", arrayOf(), false, TestAnnotatedClass::class.java, listOf())
        assertFalse { comparer.compare(handler, anotherHandler) }
    }
}