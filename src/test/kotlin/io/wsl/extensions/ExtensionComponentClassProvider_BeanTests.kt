package io.wsl.extensions

import io.wsl.tests.SpringTests
import io.wsl.tests.TestComponent
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class ExtensionComponentClassProvider_BeanTests {
    @Autowired
    lateinit var provider: ExtensionComponentClassProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }
}