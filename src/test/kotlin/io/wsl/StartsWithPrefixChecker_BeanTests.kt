package io.wsl

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class StartsWithPrefixChecker_BeanTests {
    @Autowired
    lateinit var checker: StartsWithPrefixChecker

    @Test
    fun `not null`() {
        assertNotNull(checker)
    }

    @ParameterizedTest
    @CsvSource("A,Anetka", "Kac,Kacperek", "Mam,Mama", "0, 05helloWorld", "05helloW, 05helloWorld")
    fun `check returns true if expected`(prefix: String, str: String) {
        assertTrue(checker.check(str, prefix))
    }

    @ParameterizedTest
    @CsvSource("Anetka,Awionetka", "Test,TTT", "05,06")
    fun `check returns false if expected`(prefix: String, str: String) {
        assertFalse(checker.check(str, prefix))
    }

    @ParameterizedTest
    @ValueSource(strings = ["Anetka", "Test", "0505", "KacperekIAnetka", "HelloWorld"])
    fun `check returns true if prefix is equals to str`(e: String) {
        assertTrue(checker.check(e, e))
    }
}