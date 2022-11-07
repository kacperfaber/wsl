package io.wsl.parameter_validator_extension_executor

import io.wsl.executor.InvokeParameter
import io.wsl.extensions.ExtensionComponent
import io.wsl.model_state.ModelState

interface ParameterValidatorExtensionExecutor {
    fun execute(invokeParameter: InvokeParameter, modelState: ModelState, extensionComponentClass: Class<out ExtensionComponent>, annotation: Annotation)
}