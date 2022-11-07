package io.wsl.extensions

import io.wsl.invoke_parameter.InvokeParameter
import io.wsl.model_state.ModelState

abstract class ParameterValidator : ExtensionComponent() {
    abstract fun validate(invokeParameter: InvokeParameter, modelState: ModelState, annotation: Annotation)
}