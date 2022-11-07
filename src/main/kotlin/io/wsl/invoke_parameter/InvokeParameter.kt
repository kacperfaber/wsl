package io.wsl.invoke_parameter

import io.wsl.extensions.ExtensionModel

class InvokeParameter(val type: Class<*>, val value: Any?, val extensions: List<ExtensionModel>)