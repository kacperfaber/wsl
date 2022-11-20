package io.wsl.model

import org.springframework.context.annotation.*

@Configuration
open class WslModelBean {
    @Bean
    @Conditional(GlobalConfigClassBeanRegistered::class)
    open fun getModelBean(wslModelProvider: WslModelProvider): WslModel {
        return wslModelProvider.provide()
    }
}