package io.wsl.globalcfg

import io.wsl.BeanOrNullProvider
import io.wsl.GlobalConfigClass
import io.wsl.exceptions.NoGlobalConfigClassException
import org.springframework.stereotype.Component

@Component
class GlobalConfigClassBeanProviderImpl(var beanOrNullProvider: BeanOrNullProvider) : GlobalConfigClassBeanProvider {
    override fun provide(): GlobalConfigClass {
        return beanOrNullProvider.provide(GlobalConfigClass::class.java) ?: throw NoGlobalConfigClassException()
    }
}