package io.wsl.exceptions

import io.wsl.tests.assertIsException
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ControllerNotRegistered_BeanTests {
    @Test
    fun `is exception`() {
        assertIsException(ControllerNotRegistered::class.java)
    }
    
    @Test
    fun `has no empty constructor`() {
        assertTrue { ControllerNotRegistered::class.java.constructors.none { it.parameterCount == 0 } }
    }

    @Test
    fun `has constructor with single Class parameter`() {
        assertTrue { ControllerNotRegistered::class.java.constructors.any {it.parameters[0].type == Class::class.java} }
    }
}