package io.wsl.org_reflections

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertNotNull

@SpringTests
@Suppress("DEPRECATION")
class ReflectionsForPackageScanProvider_BeanTests {
    @Autowired
    lateinit var provider: ReflectionsForPackageScanProvider

    @Test
    fun `not null`() {
        assertNotNull(provider)
    }

    @Test
    fun `provide does not throw`() {
        assertDoesNotThrow { provider.provide(listOf("io.wsl.tests")) }
    }

    @Test
    fun `provide returns not null`() {
        assertNotNull(provider.provide(listOf("io.wsl.tests")))
    }

    @Test
    fun `provide returns configuration not null`() {
        val res = provider.provide(listOf("io.wsl.tests"))
        assertNotNull(res.configuration)
    }

    @Test
    fun `provide returns instance that will not throw when 'allTypes' called`() {
        val res = provider.provide(listOf("io.wsl.tests"))
        assertDoesNotThrow { res.allTypes }
    }

    @Test
    fun `provide returns instance that will return only class names starts from expected package name when 'allTypes' called`() {
        val `package` = "io.wsl.tests"
        val res = provider.provide(listOf(`package`))
        res.allTypes.all { it.startsWith(`package`) }
    }
}