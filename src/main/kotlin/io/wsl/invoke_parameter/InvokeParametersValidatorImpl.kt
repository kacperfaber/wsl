package io.wsl.invoke_parameter

import io.wsl.model_state.ModelState
import org.springframework.stereotype.Component

@Component
class InvokeParametersValidatorImpl(var invokeParameterValidator: InvokeParameterValidator) : InvokeParametersValidator {
    override fun doValidation(invokeParameters: List<InvokeParameter>, modelState: ModelState) {
        for (invokeParameter in invokeParameters)
            invokeParameterValidator.validate(invokeParameter, modelState)
    }
}