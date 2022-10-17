package io.wsl.cfg

import io.wsl.globalcfg.GlobalConfigProvider
import org.springframework.stereotype.Component

// TODO: Create another ConfigProvider implementation to create better Config's [with includes]

@Component
class SimpleConfigProvider(var globalConfigProvider: GlobalConfigProvider) : ConfigProvider {
    override fun provide(globalConfigClass: Class<*>): Config {
        val globalConfig = globalConfigProvider.provide(globalConfigClass)
        return Config(globalConfig.scanPackage, globalConfig.scanPrefix, globalConfig.extensions)
    }
}