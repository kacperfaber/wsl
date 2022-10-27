package io.wsl.path_variables

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class PathVariablePathDeclarationToRegexConverter_BeanTests {
    @Autowired
    lateinit var converter: PathVariablePathDeclarationToRegexConverter
    @MockBean
    lateinit var typeConverter: PathDeclarationTypeToRegexConverter


    @Test
    fun `not null`() {
        assertNotNull(converter)
    }

    @Test
    fun `convert does not throw`() {
        `when`(typeConverter.convert(any())).thenReturn("")
        assertDoesNotThrow { converter.convert("", "") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["hello", "world", "555", "HelloWorld"])
    fun `convert calls PathDeclarationTypeToRegexConverter with 'type' equals to given`(s: String) {
        `when`(typeConverter.convert(any())).thenReturn("")
        converter.convert("", s)
        verify(typeConverter).convert(eq(s))
    }

    @ParameterizedTest
    @ValueSource(strings = ["hello", "world", "555", "HelloWorld"])
    fun `convert returns string that contains what PathDeclarationTypeToRegexConverter returned`(s: String) {
        `when`(typeConverter.convert(any())).thenReturn(s)
        assertTrue { converter.convert("", s).contains(s) }
    }

    @ValueSource(strings = ["hello", "world", "555", "HelloWorld"])
    @ParameterizedTest
    fun `convert returns string match to template`(type: String) {
        val name = "ExampleName"
        `when`(typeConverter.convert(any())).thenReturn(type)
        assertEquals("(?'$name'$type)", converter.convert(name, type))
    }
}