package io.wsl.actions

import io.wsl.extensions.ExtensionModel
import java.lang.reflect.Method

// TODO: Where we put the 'parameters extension'?
class Action(var method: Method, var clazz: Class<*>, var name: String, var extensions: List<ExtensionModel>)