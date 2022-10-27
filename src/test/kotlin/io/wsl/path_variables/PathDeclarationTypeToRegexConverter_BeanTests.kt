package io.wsl.path_variables

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@SpringTests
class PathDeclarationTypeToRegexConverter_BeanTests {
    @Autowired
    lateinit var converter: PathDeclarationTypeToRegexConverter

    companion object {
        @JvmStatic
        fun allTypes(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(VariableDeclarationTypes.BINARY),
                Arguments.of(VariableDeclarationTypes.BOOLEAN),
                Arguments.of(VariableDeclarationTypes.STRING),
                Arguments.of(VariableDeclarationTypes.FLOAT),
                Arguments.of(VariableDeclarationTypes.NEGATIVE_INT),
                Arguments.of(VariableDeclarationTypes.POSITIVE_INT),
                Arguments.of(VariableDeclarationTypes.INT)
            )
        }
    }

    @ParameterizedTest
    @MethodSource("allTypes")
    fun `not empty`(type: String) {
        assertNotEquals("", converter.convert(type))
    }

    @Test
    fun `expected pattern when VariableDeclarationTypes-BINARY`() {
        assertEquals("0|1", converter.convert(VariableDeclarationTypes.BINARY))
    }

    @Test
    fun `expected pattern when VariableDeclarationTypes-BOOLEAN`() {
        assertEquals("true|false", converter.convert(VariableDeclarationTypes.BOOLEAN))
    }

    @Test
    fun `expected pattern when VariableDeclarationTypes-STRING`() {
        assertEquals(".+", converter.convert(VariableDeclarationTypes.STRING))
    }

    @Test
    fun `expected pattern when VariableDeclarationTypes-INT`() {
        assertEquals("\\+?\\-?\\d+", converter.convert(VariableDeclarationTypes.INT))
    }

    @Test
    fun `expected pattern when VariableDeclarationTypes-POSITIVE_INT`() {
        assertEquals("\\+?\\d+", converter.convert(VariableDeclarationTypes.POSITIVE_INT))
    }

    @Test
    fun `expected pattern when VariableDeclarationTypes-NEGATIVE_INT`() {
        assertEquals("-\\d+", converter.convert(VariableDeclarationTypes.NEGATIVE_INT))
    }

    @Test
    fun `expected pattern when VariableDeclarationTypes-FLOAT`() {
        assertEquals("[0-9\\.]+", converter.convert(VariableDeclarationTypes.FLOAT))
    }

    @ParameterizedTest
    @ValueSource(strings = ["helloWorld", "ak47", "csgo", "chesses", "kiss"])
    fun `throws UnresolvedPathVariableType exception when given string should not be recognized`(s: String) {
        assertThrows<UnresolvedPathVariableType> { converter.convert(s) }
    }
}