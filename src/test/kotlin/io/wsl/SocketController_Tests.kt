package io.wsl

import io.wsl.tests.assertAnnotationRetentionIs
import io.wsl.tests.assertIsAnnotationClass
import io.wsl.tests.assertOnlyAnnotationTargetIs
import org.junit.jupiter.api.Test

class SocketController_Tests {
    @Test
    fun `is annotation class`() {
        assertIsAnnotationClass(SocketController::class.java)
    }

    @Test
    fun `only annotation target is AnnotationTarget_CLASS`() {
        assertOnlyAnnotationTargetIs(SocketController::class.java, AnnotationTarget.CLASS)
    }

    @Test
    fun `retention is runtime`() {
        assertAnnotationRetentionIs(SocketController::class.java, AnnotationRetention.RUNTIME)
    }
}