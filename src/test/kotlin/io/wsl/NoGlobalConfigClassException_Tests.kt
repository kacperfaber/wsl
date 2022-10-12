package io.wsl

import io.wsl.exceptions.NoGlobalConfigClassException
import io.wsl.tests.assertIsException
import org.junit.jupiter.api.Test

class NoGlobalConfigClassException_Tests {
    @Test
    fun `it is exception`() {
        assertIsException(NoGlobalConfigClassException::class.java)
    }
}