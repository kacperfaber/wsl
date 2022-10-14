package io.wsl.events

import io.wsl.BeanOrNullProvider
import io.wsl.exceptions.EventHandlerInvokerException
import io.wsl.reflections.MethodInvoker
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class MethodEventHandlerInvokerImpl(var beanProvider: BeanOrNullProvider, var methodInvoker: MethodInvoker) : MethodEventHandlerInvoker {
    override fun invoke(parameterInstance: Any, componentClass: Class<*>, method: Method) {
        val component  = beanProvider.provide(componentClass) ?: throw EventHandlerInvokerException()
        methodInvoker.invoke(method, component, parameterInstance)
    }
}