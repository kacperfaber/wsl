package io.wsl

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Suppress("RemoveRedundantSpreadOperator")
@Configuration
@ComponentScan(*["io.wsl.*", "io.wsl"])
open class WslSpringConfig