package io.wsl.handlers

import io.wsl.tests.HandlerAnnotatedClass
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HandlerIsDefaultCheckerImpl_check_Tests {
    lateinit var handlerIsDefaultChecker: HandlerIsDefaultCheckerImpl

    @BeforeEach
    fun beforeEach() {
        handlerIsDefaultChecker = HandlerIsDefaultCheckerImpl()
    }

    private fun exec(handler: Handler): Boolean = handlerIsDefaultChecker.check(handler)

    @Test
    fun `does not throw`() {
        assertDoesNotThrow { exec(Handler("path", arrayOf(""), true, HandlerAnnotatedClass::class.java, mutableListOf() )) }
    }

    @Test
    fun `true if Handler-isDefault`() {
        assertTrue(exec(Handler("path", arrayOf(""), true, HandlerAnnotatedClass::class.java, mutableListOf() )))
        assertTrue(exec(Handler("helloWorld", arrayOf("test", "test"), true, HandlerAnnotatedClass::class.java, mutableListOf() )))
        assertTrue(exec(Handler("World", arrayOf("test"), true, HandlerAnnotatedClass::class.java, mutableListOf() )))
        assertTrue(exec(Handler("kacperek", arrayOf(""), true, HandlerAnnotatedClass::class.java, mutableListOf() )))
    }

    @Test
    fun `false if Handler-isDefault`() {
        assertFalse(exec(Handler("path", arrayOf(""), false, HandlerAnnotatedClass::class.java, mutableListOf() )))
        assertFalse(exec(Handler("pathttt", arrayOf("ttt"), false, HandlerAnnotatedClass::class.java, mutableListOf() )))
        assertFalse(exec(Handler("", arrayOf("ttt"), false, HandlerAnnotatedClass::class.java, mutableListOf() )))
    }
}