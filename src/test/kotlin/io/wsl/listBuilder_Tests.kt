package io.wsl

import io.wsl.tests.TestError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class listBuilder_Tests {
    fun <T> exec(action: ListBuilder<T>.() -> Unit): MutableList<T> {
        return listBuilder(action)
    }

    @Test
    fun `does not throw`() {
        assertDoesNotThrow { exec<Int> { } }
    }

    @Test
    fun `returns expected list length`() {
        val res = exec<Int> {
            yield(5)
            yield(10)
            yield(15)
        }

        assertEquals(3, res.count())
    }

    @Test
    fun `function calls action lambda`() {
        assertThrows<TestError> {
            exec<Int> {
                throw TestError()
            }
        }
    }

    @Test
    fun `function calls action lambda with not null ListBuilder instance`() {
        exec<Int> {
            assertNotNull(this)
        }
    }
}