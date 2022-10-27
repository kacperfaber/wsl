@file:Suppress("SpellCheckingInspection")

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

@SpringTests
class RegexProvider_BeanTests {
    @Autowired
    lateinit var provider: RegexProvider

    @MockBean
    lateinit var patternProvider: RegexPatternProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw for simple name`() {
        `when`(patternProvider.provide(any())).thenReturn("")
        assertDoesNotThrow { provider.provide("user/delete") }
    }

    @Test
    fun `provide returns not null`() {
        `when`(patternProvider.provide(any())).thenReturn("")
        assertNotNull(provider.provide("user/delete"))
    }

    @ParameterizedTest
    @ValueSource(strings = ["user.read", "user/create", "kacperek+anetka", "pathvars", "95959"])
    fun `provide returns Regex-pattern equals to returned by RegexPatternProvider`(s: String) {
        `when`(patternProvider.provide(any())).thenReturn(s)
        assertEquals(s, provider.provide("").pattern)
    }

    @ParameterizedTest
    @ValueSource(strings = ["user.read", "user/create", "kacperek+anetka", "pathvars", "95959"])
    fun `provide calls RegexPatternProvider with 'actionNameOrPath' equals to given`(s: String) {
        `when`(patternProvider.provide(any())).thenReturn(s)
        provider.provide(s)
        verify(patternProvider).provide(eq(s))
    }
}