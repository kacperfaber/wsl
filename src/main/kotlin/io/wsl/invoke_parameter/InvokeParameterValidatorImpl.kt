package io.wsl.invoke_parameter

import io.wsl.extensions.ExtensionKind
import io.wsl.model_state.ModelState
import io.wsl.parameter_validator_extension_executor.ParameterValidatorExtensionExecutor
import org.springframework.stereotype.Component

@Component
class InvokeParameterValidatorImpl(var parameterValidatorExtensionExecutor: ParameterValidatorExtensionExecutor) :
    InvokeParameterValidator {
    override fun validate(invokeParameter: InvokeParameter, modelState: ModelState) {
        for (ext in invokeParameter.extensions) {
            if (ext.extensionKind == ExtensionKind.ParameterValidator) {
                parameterValidatorExtensionExecutor.execute(invokeParameter, modelState, ext.componentClass, ext.annotation)
            }
        }
    }
}