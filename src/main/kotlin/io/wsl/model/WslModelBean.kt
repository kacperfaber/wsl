package io.wsl.model

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class WslModelBean {
    @Bean
    open fun getModelBean(wslModelProvider: WslModelProvider): WslModel {
        return wslModelProvider.provide()
    }
}