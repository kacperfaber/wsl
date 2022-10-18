package io.wsl.globalcfg

import io.wsl.BeanOrNullProvider
import io.wsl.GlobalConfigClass
import io.wsl.exceptions.NoGlobalConfigClassException
import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.mockito.internal.verification.Times
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringTests
class GlobalConfigClassBeanProvider_BeanTests  {
    @Autowired
    lateinit var classProvider: GlobalConfigClassBeanProvider
    
    @MockBean
    lateinit var beanOrNullProvider: BeanOrNullProvider
    
    @Test
    fun `not null`() {
        assertNotNull(classProvider)
    }
    
    @Test
    fun `provide does not throw when BeanOrNullProvider returned instance`() {
        `when`(beanOrNullProvider.provide(any<Class<GlobalConfigClass>>())).thenReturn(GlobalConfigClass(Int::class.java))
        assertDoesNotThrow { classProvider.provide() }
    }
    
    @Test
    fun `provide throws NoGlobalConfigClassException when BeanOrNullProvider returned null`() {
        `when`(beanOrNullProvider.provide(any<Class<GlobalConfigClass>>())).thenReturn(null)
        assertThrows<NoGlobalConfigClassException> { classProvider.provide() }
    }

    @Test
    fun `provide returns instance returned by BeanOrNullProvider when its not null`() {
        val expected = GlobalConfigClass(Int::class.java)
        `when`(beanOrNullProvider.provide(any<Class<GlobalConfigClass>>())).thenReturn(expected)
        assertEquals(expected, classProvider.provide())
    }

    @Test
    fun `provide calls BeanOrNullProvider once`() {
        `when`(beanOrNullProvider.provide(any<Class<GlobalConfigClass>>())).thenReturn(GlobalConfigClass(Int::class.java))
        classProvider.provide()
        verify(beanOrNullProvider, Times(1)).provide(any<Class<*>>())
    }

    @Test
    fun `provide calls BeanOrNullProvider with target class equals to GlobalConfigClass`() {
        `when`(beanOrNullProvider.provide(any<Class<GlobalConfigClass>>())).thenReturn(GlobalConfigClass(Int::class.java))
        classProvider.provide()
        verify(beanOrNullProvider).provide(eq(GlobalConfigClass::class.java))
    }
}