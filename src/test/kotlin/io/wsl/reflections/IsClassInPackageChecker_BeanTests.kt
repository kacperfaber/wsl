package io.wsl.reflections

import io.wsl.tests.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.reflect.KClass
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringTests
class IsClassInPackageChecker_BeanTests {
    @Autowired
    lateinit var checker: IsClassInPackageChecker
    
    @MockBean
    lateinit var packageNameProvider: ClassPackageNameProvider
    
    @Test
    fun `not null`() {
        assertNotNull(checker)
    }
    
    @Test
    fun `check does not throw`() {
        `when`(packageNameProvider.provide(any())).thenReturn("hello.world")
        assertDoesNotThrow { checker.check(TestComponent::class.java, "hello.world") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["hello.world", "io.wsl.test", "io.wsl.tests", "ios", "android", "anetka_skarb"])
    fun `check returns true if packageNameProvider returned expected package name`(expected: String) {
        `when`(packageNameProvider.provide(any())).thenReturn(expected)
        assertTrue(checker.check(TestComponent::class.java, expected))
    }

    @ParameterizedTest
    @CsvSource("io.test, io.test.xd", "hello.world,world.hello", "anetka,kacperek")
    fun `check returns false if packageNameProvider returned another package name`(toReturn: String, expected: String) {
        `when`(packageNameProvider.provide(any())).thenReturn(toReturn)
        assertFalse(checker.check(TestComponent::class.java, expected))
    }

    @ParameterizedTest
    @Suppress("Junit5MalformedParameterized")
    @ValueSource(classes = [TestComponent::class, ParameterizedMethods::class, TestAnnotatedClass::class, TestError::class, WithMethodClass::class])
    fun `ClassPackageNameProvider gets class equals to given`(clazz: Class<*>) {
        checker.check(clazz, "hello.world")
        verify(packageNameProvider).provide(eq(clazz))
    }
}