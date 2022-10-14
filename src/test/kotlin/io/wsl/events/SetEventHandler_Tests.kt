package io.wsl.events

import io.wsl.tests.assertAnnotationRetentionIs
import io.wsl.tests.assertIsAnnotationClass
import io.wsl.tests.assertOnlyAnnotationTargetIs
import org.junit.jupiter.api.Test

class SetEventHandler_Tests {
    @Test
    fun `is annotation class`() {
        assertIsAnnotationClass(SetEventHandler::class.java)
    }

    @Test
    fun `only target is ANNOTATION_CLASS`() {
        assertOnlyAnnotationTargetIs(SetEventHandler::class.java, AnnotationTarget.ANNOTATION_CLASS)
    }

    @Test
    fun `retention is RUNTIME`() {
        assertAnnotationRetentionIs(SetEventHandler::class.java, AnnotationRetention.RUNTIME)
    }
}