package io.wsl

import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class BeanProviderImpl(var applicationContext: ApplicationContext) : BeanProvider {
    override fun <T> provide(clazz: Class<T>): T {
        return applicationContext.getBean(clazz)
    }
}