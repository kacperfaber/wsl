package io.wsl.extensions

import io.wsl.executor.InvokeParameter
import io.wsl.model_state.ModelState

abstract class ParameterValidator : ExtensionComponent() {
    abstract fun validate(invokeParameter: InvokeParameter, modelState: ModelState, annotation: Annotation)
}