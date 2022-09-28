package io.wsl

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ListBuilder_yield_Tests {
    fun <T> exec(builder: ListBuilder<T>, t: T) {
        builder.yield(t)
    }

    @Test
    fun `does not throw`() {
        val builder = ListBuilder<Int>()
        assertDoesNotThrow { exec(builder, 5) }
    }

    @Test
    fun `makes ListBuilder list greater`() {
        val builder = ListBuilder<Int>()
        val before  = builder.list.count()
        exec(builder, 5)
        val after = builder.list.count()
        assertTrue { before < after }
    }

    @Test
    fun `makes ListBuilder list greater by 1`() {
        val builder = ListBuilder<Int>()
        val before  = builder.list.count()
        exec(builder, 5)
        val after = builder.list.count()
        assertEquals(before + 1, after)
    }

    @Test
    fun `after execute ListBuilder list contains added element`() {
        val builder = ListBuilder<Int>()
        assertFalse(builder.list.any {it == 5})
        exec(builder, 5)
        assertTrue(builder.list.any {it == 5})
    }

    @Test
    fun `call yield many times does not throw an exception`() {
        val builder = ListBuilder<Int>()
        assertDoesNotThrow {
            exec(builder, 5)
            exec(builder, 10)
            exec(builder, 15)
        }
    }
}