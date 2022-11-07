package io.wsl.executor

import io.wsl.extensions.ExtensionModel

class InvokeParameter(val type: Class<*>, val value: Any?, val extensions: List<ExtensionModel>)