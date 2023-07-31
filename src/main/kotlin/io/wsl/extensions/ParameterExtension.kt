package io.wsl.extensions

import io.wsl.action_call.ActionCall
import org.springframework.web.socket.WebSocketSession

abstract class ParameterExtension : ExtensionComponent() {
    abstract fun getValue(actionCall: ActionCall, parameterType: Class<*>, annotation: Annotation, session: WebSocketSession): Any?
}