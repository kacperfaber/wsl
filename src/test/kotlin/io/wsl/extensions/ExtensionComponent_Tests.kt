package io.wsl.extensions

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ExtensionComponent_Tests {
    @Test
    fun `is open class`() {
        assertTrue { ExtensionComponent::class.isOpen }
    }
}