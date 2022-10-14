package io.wsl

import io.wsl.tests.assertAnnotationRetentionIs
import io.wsl.tests.assertIsAnnotationClass
import io.wsl.tests.assertOnlyAnnotationTargetIs
import org.junit.jupiter.api.Test

class EventHandler_Tests {
    @Test
    fun `is annotation class`() {
        assertIsAnnotationClass(EventHandler::class.java)
    }

    @Test
    fun `only target is FUNCTION`() {
        assertOnlyAnnotationTargetIs(EventHandler::class.java, AnnotationTarget.FUNCTION)
    }

    @Test
    fun `retention is RUNTIME`() {
        assertAnnotationRetentionIs(EventHandler::class.java, AnnotationRetention.RUNTIME)
    }
}