package io.wsl.tests

import io.wsl.WslSpringConfig
import org.springframework.test.context.ContextConfiguration

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ContextConfiguration(classes = [WslSpringConfig::class])
annotation class SpringTests()