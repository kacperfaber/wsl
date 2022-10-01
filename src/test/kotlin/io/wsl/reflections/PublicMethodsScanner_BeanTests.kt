package io.wsl.reflections

import io.wsl.tests.SpringTests
import io.wsl.tests.WithMethodClass
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import java.lang.reflect.Modifier
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class PublicMethodsScanner_BeanTests {
    @Autowired
    lateinit var scanner: PublicMethodsScanner

    @Test
    fun `not null`() {
        assertNotNull(scanner)
    }

    @Test
    fun `scan does not throw`() {
        assertDoesNotThrow { scanner.scan(WithMethodClass::class.java) }
    }

    @Test
    fun `scan returns not null`() {
        assertNotNull(scanner.scan(WithMethodClass::class.java))
    }

    @ParameterizedTest
    @ValueSource(strings = ["toString", "equals", "hashCode", "notify", "notifyAll", "getClass"])
    fun `scan returns expected method name`(expectedName: String) {
        assertTrue(scanner.scan(WithMethodClass::class.java).any { it.name == expectedName })
    }

    @ParameterizedTest
    @ValueSource(strings = ["toString", "equals", "hashCode", "notify", "notifyAll", "getClass"])
    fun `is only one method with expected method name`(expectedName: String) {
        assertTrue(scanner.scan(WithMethodClass::class.java).count { it.name == expectedName } == 1)
    }
}