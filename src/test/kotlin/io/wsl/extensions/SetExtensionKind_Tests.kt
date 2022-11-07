package io.wsl.extensions

import io.wsl.tests.assertAnnotationRetentionIs
import io.wsl.tests.assertIsAnnotationClass
import io.wsl.tests.assertOnlyAnnotationTargetIs
import org.junit.jupiter.api.Test

class SetExtensionKind_Tests {
    @Test
    fun `is annotation class`() {
        assertIsAnnotationClass(SetExtensionKind::class.java)
    }

    @Test
    fun `retention is RUNTIME`() {
        assertAnnotationRetentionIs(SetExtensionKind::class.java, AnnotationRetention.RUNTIME)
    }

    @Test
    fun `only allowed annotation target is ANNOTATION_CLASS`() {
        assertOnlyAnnotationTargetIs(SetExtensionKind::class.java, AnnotationTarget.ANNOTATION_CLASS)
    }
}