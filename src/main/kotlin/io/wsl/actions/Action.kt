package io.wsl.actions

import io.wsl.extensions.ExtensionModel
import io.wsl.parameters.ParameterList
import java.lang.reflect.Method

class Action(var method: Method, var controllerClass: Class<*>, var name: String, var extensions: List<ExtensionModel>, var parameterList: ParameterList, var pattern: Regex)