package io.wsl.tests

import io.wsl.WslSpringConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ContextConfiguration(classes = [WslSpringConfig::class])
@SpringBootTest
annotation class SpringTests()