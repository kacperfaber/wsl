package io.wsl.invoke_parameter

import io.wsl.model_state.ModelState

interface InvokeParametersValidator {
    fun doValidation(invokeParameters: List<InvokeParameter>, modelState: ModelState)
}