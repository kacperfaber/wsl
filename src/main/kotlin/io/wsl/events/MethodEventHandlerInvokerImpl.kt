package io.wsl.events

import io.wsl.BeanOrNullProvider
import io.wsl.exceptions.EventHandlerInvokerException
import io.wsl.reflections.MethodInvoker
import org.springframework.stereotype.Component

@Component
class MethodEventHandlerInvokerImpl(var beanProvider: BeanOrNullProvider, var methodInvoker: MethodInvoker) : MethodEventHandlerInvoker {
    override fun invoke(eventHandler: MethodEventHandler, parameterInstance: Any) {
        val component  = beanProvider.provide(eventHandler.componentClass) ?: throw EventHandlerInvokerException()
        methodInvoker.invoke(eventHandler.method, component, listOf(parameterInstance))
    }
}