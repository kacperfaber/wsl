package io.wsl.action_call

import java.lang.reflect.Method

class ActionCall(
    var parameters: Map<Class<*>, Any?>,
    var method: Method,
    var controllerInstance: Any,
    val messageName: String,
    val messageData: String
)