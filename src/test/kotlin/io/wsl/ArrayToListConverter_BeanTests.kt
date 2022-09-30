package io.wsl

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class ArrayToListConverter_BeanTests {
    @Autowired
    lateinit var converter: ArrayToListConverter
    
    @Test
    fun `not null`() {
        assertNotNull(converter)
    }

    @Test
    fun `convert method does not throw`() {
        assertDoesNotThrow { converter.convert(arrayOf(1,2,3)) }
    }
    
    @Test
    fun `returns the same items count to given array`() {
        val arr = arrayOf(1,2,3)
        val res = converter.convert(arr)
        assertEquals(arr.count(), res.count())
    }

    @Test
    fun `convert result contains items from given array`() {
        val arr = arrayOf(1,2,3)
        val res = converter.convert(arr)
        assertTrue(arr.all {
            res.contains(it)
        })
    }
}