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
    fun `only target is ANNOTATION_CLASS`() {
        assertOnlyAnnotationTargetIs(EventHandler::class.java, AnnotationTarget.ANNOTATION_CLASS)
    }

    @Test
    fun `retention is RUNTIME`() {
        assertAnnotationRetentionIs(EventHandler::class.java, AnnotationRetention.RUNTIME)
    }
}