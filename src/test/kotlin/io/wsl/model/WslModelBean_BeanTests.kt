package io.wsl.model

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringTests
class WslModelBean_BeanTests {
    @Autowired
    lateinit var appContext: ApplicationContext

    @Test
    fun `not null when WslSpringConfig`() {
        assertNotNull(appContext.getBean(WslModel::class.java))
    }
}

@SpringTests
class WslModelBean_BeanTests_another_configuration {
    @Autowired
    lateinit var appContext: ApplicationContext

    @Test
    fun `null when WslSpringConfig not present`() {
        assertNull(appContext.getBean(WslModel::class.java))
    }
}