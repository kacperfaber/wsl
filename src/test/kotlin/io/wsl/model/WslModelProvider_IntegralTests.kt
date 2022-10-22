package io.wsl.model

import io.wsl.DefaultHandler
import io.wsl.GlobalConfigClass
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import tests.example_project_model.ChatController
import tests.example_project_model.Global
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
@TestConfiguration
open class WslModelProvider_IntegralTests {
    @Autowired
    lateinit var wslModelProvider: WslModelProvider

    @Bean
    open fun globalConfigClass(): GlobalConfigClass {
        return GlobalConfigClass(Global::class.java)
    }

    @Test
    fun `does not throw`() {
        assertDoesNotThrow { wslModelProvider.provide() }
    }

    @Test
    fun `not null`() {
        assertNotNull(wslModelProvider.provide())
    }

    @Test
    fun `expected actions count`() {
        val wslModel = wslModelProvider.provide()
        assertEquals(2, wslModel.actions.count())
    }

    @ParameterizedTest
    @ValueSource(strings = ["ping", "pong"])
    fun `has actions with name`(name: String) {
        val wslModel = wslModelProvider.provide()
        assertTrue(wslModel.actions.any {it.name == name})
    }
    
    @Test
    fun `has ChatController class`() {
        val wslModel = wslModelProvider.provide()
        assertTrue(wslModel.controllers.any {it.clazz == ChatController::class.java})
    }
}