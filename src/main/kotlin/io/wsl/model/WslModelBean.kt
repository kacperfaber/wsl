package io.wsl.model

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// TODO: Cause's exceptions in tests, because in tests there's no GlobalConfig created.

@Configuration
open class WslModelBean {
    @Bean
    open fun getModelBean(wslModelProvider: WslModelProvider): WslModel {
        return wslModelProvider.provide()
    }
}