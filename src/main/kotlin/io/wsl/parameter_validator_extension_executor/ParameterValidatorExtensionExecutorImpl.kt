package io.wsl.parameter_validator_extension_executor

import io.wsl.BeanOrNullProvider
import io.wsl.exceptions.ExtensionComponentNotRegistered
import io.wsl.executor.InvokeParameter
import io.wsl.extensions.ExtensionComponent
import io.wsl.extensions.ParameterValidator
import io.wsl.model_state.ModelState
import org.springframework.stereotype.Component

@Component
class ParameterValidatorExtensionExecutorImpl(var beanOrNullProvider: BeanOrNullProvider) : ParameterValidatorExtensionExecutor {
    @Suppress("UNCHECKED_CAST")
    override fun execute(invokeParameter: InvokeParameter, modelState: ModelState, extensionComponentClass: Class<out ExtensionComponent>) {
        val bean = beanOrNullProvider.provide(extensionComponentClass as Class<out ParameterValidator>)
            ?: throw ExtensionComponentNotRegistered()

        return bean.validate(invokeParameter, modelState)
    }
}