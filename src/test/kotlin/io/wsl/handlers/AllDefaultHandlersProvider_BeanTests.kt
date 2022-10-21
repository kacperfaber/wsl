package io.wsl.handlers

import io.wsl.tests.HandlerAnnotatedClass
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.`when`
import org.mockito.internal.verification.Times
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class AllDefaultHandlersProvider_BeanTests {
    @Autowired
    lateinit var provider: AllDefaultHandlersProvider

    @MockBean
    lateinit var isDefaultChecker: HandlerIsDefaultChecker

    val exampleHandler = Handler(
        "", arrayOf(), false,
        HandlerAnnotatedClass::class.java,
        mutableListOf()
    )
    
    @Test
    fun `not null`() {
        assertNotNull(provider)
    }
    
    @Test
    fun `provide does not throw`() {
        assertDoesNotThrow { provider.provide(listOf()) }
    }

    @ParameterizedTest
    @ValueSource(ints = [5,10,15,22,0,1,3])
    fun `provide calls handlerIsDefaultChecker for each list item`(x: Int) {
        val list = mutableListOf<Handler>()
        repeat(x) {
            list.add(exampleHandler)
        }
        provider.provide(list)
        verify(isDefaultChecker, Times(x)).check(any())
    }

    @ParameterizedTest
    @ValueSource(ints = [5,10,15,22,0,1,3])
    fun `provide returns all given when handlerIsDefaultChecker returns always true`(forListWithXItems: Int) {
        val list = mutableListOf<Handler>()
        repeat(forListWithXItems) {
            list.add(exampleHandler)
        }
        `when`(isDefaultChecker.check(any())).thenReturn(true)
        assertEquals(forListWithXItems, provider.provide(list).count())
    }

    @ParameterizedTest
    @ValueSource(ints = [5,10,15,22,0,1,3])
    fun `provide returns empty list when handlerIsDefaultChecker returns always false`(forListWithXItems: Int) {
        val list = mutableListOf<Handler>()
        repeat(forListWithXItems) {
            list.add(exampleHandler)
        }
        `when`(isDefaultChecker.check(any())).thenReturn(false)
        assertTrue(provider.provide(list).isEmpty())
    }
}