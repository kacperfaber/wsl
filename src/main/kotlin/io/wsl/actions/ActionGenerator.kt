package io.wsl.actions

import io.wsl.extensions.ExtensionModel
import io.wsl.parameters.ParameterList
import java.lang.reflect.Method

interface ActionGenerator {
    fun generate(name: String,
                 method: Method,
                 controllerClass: Class<*>,
                 parameterList: ParameterList,
                 extensions: List<ExtensionModel>,
                 pattern: Regex): Action
}