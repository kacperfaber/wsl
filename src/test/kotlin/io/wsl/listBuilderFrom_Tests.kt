package io.wsl

import io.wsl.tests.TestError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class listBuilderFrom_Tests {
    fun <TX> exec(initialList: MutableList<TX>, action: ListBuilder<TX>.() -> Unit): MutableList<TX> {
        return listBuilderFrom(initialList, action)
    }

    @Test
    fun `does not throw`() {
        assertDoesNotThrow { exec<Int>(mutableListOf()) { } }
    }

    @Test
    fun `returns expected list length`() {
        val res = exec<Int>(mutableListOf()) {
            yield(5)
            yield(10)
            yield(15)
        }

        assertEquals(3, res.count())
    }

    @Test
    fun `function calls action lambda`() {
        assertThrows<TestError> {
            exec<Int>(mutableListOf()) {
                throw TestError()
            }
        }
    }

    @Test
    fun `function calls action lambda with not null ListBuilder instance`() {
        exec<Int>(mutableListOf()) {
            assertNotNull(this@exec)
        }
    }

    @Test
    fun `function calls action lambda with not null ListBuilder_list`() {
        exec(mutableListOf<Int>()) {
            assertNotNull(this@exec.list)
        }
    }

    @Test
    fun `function calls action lambda with ListBuilder_list equals to given`() {
        val expected = mutableListOf<Int>()
        exec(expected) {
            assertEquals(expected, list)
        }
    }
}