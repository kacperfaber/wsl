package io.wsl.exceptions

import io.wsl.tests.assertIsException
import org.junit.jupiter.api.Test

class MoreThanOneActionParameterExtension_Tests {
    @Test
    fun `is exception`() {
        assertIsException(MoreThanOneActionParameterExtension::class.java)
    }
}