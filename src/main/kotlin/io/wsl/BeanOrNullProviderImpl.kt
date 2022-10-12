package io.wsl

import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.stereotype.Component

@Component
class BeanOrNullProviderImpl(var beanProvider: BeanProvider) : BeanOrNullProvider  {
    override fun <T> provide(clazz: Class<T>): T? {
        return try { beanProvider.provide(clazz) } catch (e: NoSuchBeanDefinitionException) {null}
    }
}