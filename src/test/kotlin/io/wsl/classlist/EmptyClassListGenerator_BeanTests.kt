package io.wsl.classlist

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertNotNull

@SpringTests
class EmptyClassListGenerator_BeanTests {
    @Autowired
    lateinit var generator: EmptyClassListGenerator

    @Test
    fun `not null`() {
        assertNotNull(generator)
    }

    @Test
    fun `generate does not throw `() {
        assertDoesNotThrow { generator.generate() }
    }

    @Test
    fun `returns not null`() {
        assertNotNull(generator.generate())
    }
}