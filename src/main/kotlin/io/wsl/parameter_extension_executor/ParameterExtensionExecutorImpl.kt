package io.wsl.parameter_extension_executor

import io.wsl.BeanOrNullProvider
import io.wsl.action_call.ActionCall
import io.wsl.exceptions.ExtensionComponentNotRegistered
import io.wsl.extensions.ParameterExtension
import org.springframework.stereotype.Component

@Component
class ParameterExtensionExecutorImpl(var beanOrNullProvider: BeanOrNullProvider) : ParameterExtensionExecutor {
    override fun execute(
        actionCall: ActionCall,
        parameterType: Class<*>,
        componentClass: Class<out ParameterExtension>,
        annotation: Annotation
    ): Any? {
        val component = beanOrNullProvider.provide(componentClass) ?: throw ExtensionComponentNotRegistered()
        return component.getValue(actionCall, parameterType, annotation)
    }
}