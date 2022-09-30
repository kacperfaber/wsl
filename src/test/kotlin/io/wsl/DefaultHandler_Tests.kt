package io.wsl

import io.wsl.tests.assertIsAnnotationClass
import io.wsl.tests.assertOnlyTargetIs
import io.wsl.tests.assertRetentionIsRuntime
import org.junit.jupiter.api.Test

class DefaultHandler_Tests {
    @Test
    fun `only annotation target is AnnotationTarget_CLASS`() {
        DefaultHandler::class.java.assertOnlyTargetIs(AnnotationTarget.CLASS)
    }

    @Test
    fun `retention is runtime`() {
        DefaultHandler::class.java.assertRetentionIsRuntime()
    }

    @Test
    fun `is annotation class`() {
        assertIsAnnotationClass(DefaultHandler::class.java)
    }
}