package io.wsl.executor

import io.wsl.BeanOrNullProvider
import io.wsl.exceptions.ControllerNotRegistered
import org.springframework.stereotype.Component

@Component
class ControllerInstanceBeanProviderImpl(var beanOrNullProvider: BeanOrNullProvider) : ControllerInstanceBeanProvider {
    override fun provide(controllerClass: Class<*>): Any {
        return beanOrNullProvider.provide(controllerClass) ?: throw ControllerNotRegistered(controllerClass)
    }
}