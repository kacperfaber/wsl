package io.wsl.reflections

import io.wsl.tests.TestAnnotation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.reflect.full.createInstance
import kotlin.test.assertEquals

class DefaultAnnotationClassProvider_Tests {
    fun exec(annotation: Annotation): Class<out Annotation> {
        return DefaultAnnotationClassProvider().provide(annotation)
    }

    @Test
    fun `does not throw`() {
        val instance = TestAnnotation::class.createInstance()
        assertDoesNotThrow { exec(instance) }
    }

    @Test
    fun `returns expected`() {
        val kClass = TestAnnotation::class
        val instance = kClass.createInstance()
        assertEquals(kClass, exec(instance).kotlin)
    }
}