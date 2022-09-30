package io.wsl

import io.wsl.tests.assertAnnotationRetentionIs
import io.wsl.tests.assertIsAnnotationClass
import io.wsl.tests.assertOnlyAnnotationTargetIs
import org.junit.jupiter.api.Test

class SetHandler_Tests {
    @Test
    fun `is annotation class`() {
        assertIsAnnotationClass(SetHandler::class.java)
    }

    @Test
    fun `only annotation target is AnnotationTarget_CLASS`() {
        assertOnlyAnnotationTargetIs(SetHandler::class.java, AnnotationTarget.CLASS)
    }

    @Test
    fun `retention is runtime`() {
        assertAnnotationRetentionIs(SetHandler::class.java, AnnotationRetention.RUNTIME)
    }
}