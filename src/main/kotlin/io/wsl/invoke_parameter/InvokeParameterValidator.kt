package io.wsl.invoke_parameter

import io.wsl.model_state.ModelState

interface InvokeParameterValidator {
    fun validate(invokeParameter: InvokeParameter, modelState: ModelState)
}