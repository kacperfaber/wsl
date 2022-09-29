package io.wsl

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Handler_Tests {
    @Test
    fun `is annotation class`() {
        assertTrue { Handler::class.java.isAnnotation }
    }

    @Test
    fun `is allowed only on class`() {
        val target = Handler::class.java.getAnnotation(Target::class.java)
        val targets = target.allowedTargets

        assertTrue { targets.size == 1 }
        assertEquals(AnnotationTarget.CLASS, targets.first())
    }

    @Test
    fun `retention set to runtime`() {
        val retention = Handler::class.java.getAnnotation(Retention::class.java)
        assertEquals(AnnotationRetention.RUNTIME, retention?.value)
    }
}