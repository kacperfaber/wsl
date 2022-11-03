package io.wsl.action_call

import java.lang.reflect.Method

class ActionCall(var parameter: Map<Class<*>, Any?>, var method: Method, var controllerInstance: Any)