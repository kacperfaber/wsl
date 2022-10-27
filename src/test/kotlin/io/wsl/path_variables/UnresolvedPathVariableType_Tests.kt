package io.wsl.path_variables

import io.wsl.tests.assertIsException
import org.junit.jupiter.api.Test

class UnresolvedPathVariableType_Tests {
    @Test
    fun `is Exception`() {
        assertIsException(UnresolvedPathVariableType::class.java)
    }
}