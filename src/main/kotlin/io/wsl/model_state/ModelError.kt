package io.wsl.model_state

import io.wsl.executor.InvokeParameter

class ModelError(val invokeParameter: InvokeParameter, val text: String)