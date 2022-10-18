package io.wsl.exceptions

import io.wsl.tests.assertIsException
import org.junit.jupiter.api.Test

class CouldNotResolveAnnotation_Tests {
    @Test
    fun `is Exception`() {
        assertIsException(CouldNotResolveAnnotation::class.java)
    }
}