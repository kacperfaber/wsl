package io.wsl

import io.wsl.tests.assertAnnotationRetentionIs
import io.wsl.tests.assertIsAnnotationClass
import io.wsl.tests.assertOnlyAnnotationTargetIs
import org.junit.jupiter.api.Test

class SocketAction_Tests {
    @Test
    fun `only target is FUNCTION`() {
        assertOnlyAnnotationTargetIs(SocketAction::class.java, AnnotationTarget.FUNCTION)
    }

    @Test
    fun `retention is runtime`() {
        assertAnnotationRetentionIs(SocketAction::class.java, AnnotationRetention.RUNTIME)
    }

    @Test
    fun `is annotation class`() {
        assertIsAnnotationClass(SocketAction::class.java)
    }
}