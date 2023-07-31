package io.wsl.parameter_extension_executor

import io.wsl.action_call.ActionCall
import io.wsl.extensions.ParameterExtension
import org.springframework.web.socket.WebSocketSession

interface ParameterExtensionExecutor {
    fun execute(actionCall: ActionCall, parameterType: Class<*>, componentClass: Class<out ParameterExtension>, annotation: Annotation, session: WebSocketSession): Any?
}